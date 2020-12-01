import json
import open3d
import trimesh
import numpy as np
from PIL import Image
from networkx.drawing.tests.test_pylab import plt
from open3d.open3d_pybind.geometry import KDTreeSearchParamHybrid
from tqdm import tqdm, trange
from scipy.ndimage import map_coordinates

from misc.post_proc import np_coor2xy, np_coorx2u, np_coory2v
from misc.panostretch import pano_connect_points
from eval_general import layout_2_depth


if __name__ == '__main__':

    import argparse
    parser = argparse.ArgumentParser(formatter_class=argparse.ArgumentDefaultsHelpFormatter)
    parser.add_argument('--img', required=True,
                        help='Image texture in equirectangular format')
    parser.add_argument('--layout', required=True,
                        help='Txt file containing layout corners (cor_id)')
    parser.add_argument('--scale', default=1, type=float,
                        help='Scale texture for visualization')
    parser.add_argument('--ignore_floor', action='store_true',
                        help='Skip rendering floor')
    parser.add_argument('--ignore_ceiling', action='store_true',
                        help='Skip rendering ceiling')
    parser.add_argument('--ignore_wall', action='store_true',
                        help='Skip rendering wall')
    parser.add_argument('--ignore_wireframe', action='store_true',
                        help='Skip rendering wireframe')
    args = parser.parse_args()

    # Reading source (texture img, cor_id txt)
    equirect_texture = Image.open(args.img)
    if args.scale != 1:
        W, H = equirect_texture.size
        W = int(W * args.scale)
        H = int(H * args.scale)
        equirect_texture = equirect_texture.resize((W, H))
    equirect_texture = np.array(equirect_texture) / 255.0
    H, W = equirect_texture.shape[:2]
    with open(args.layout) as f:
        inferenced_result = json.load(f)
    cor_id = np.array(inferenced_result['uv'], np.float32)
    cor_id[:, 0] *= W
    cor_id[:, 1] *= H

    # Show wireframe
    if not args.ignore_wireframe:
        # Convert cor_id to 3d xyz
        N = len(cor_id) // 2
        floor_z = -1.6
        floor_xy = np_coor2xy(cor_id[1::2], floor_z, W, H, floorW=1, floorH=1)
        c = np.sqrt((floor_xy**2).sum(1))
        v = np_coory2v(cor_id[0::2, 1], H)
        ceil_z = (c * np.tan(v)).mean()

        # Prepare wireframe in open3d
        assert N == len(floor_xy)
        wf_points = [[x, y, floor_z] for x, y in floor_xy] +\
                    [[x, y, ceil_z] for x, y in floor_xy]
        wf_lines = [[i, (i+1)%N] for i in range(N)] +\
                   [[i+N, (i+1)%N+N] for i in range(N)] +\
                   [[i, i+N] for i in range(N)]
        wf_colors = [[1, 0, 0] for i in range(len(wf_lines))]
        wf_line_set = open3d.geometry.LineSet()
        wf_line_set.points = open3d.utility.Vector3dVector(wf_points)
        wf_line_set.lines = open3d.utility.Vector2iVector(wf_lines)
        wf_line_set.colors = open3d.utility.Vector3dVector(wf_colors)

        # exportbox = open3d.geometry.AxisAlignedBoundingBox.create_from_points(open3d.utility.Vector3dVector(wf_points))
        # exportbox1 = open3d.geometry_to_json(exportbox)
        # exportbox = open3d.geometry.AxisAlignedBoundingBox.

    # current_transformation = np.identity(4)






    # Convert corners to layout
    depth, floor_mask, ceil_mask, wall_mask = layout_2_depth(cor_id, H, W, return_mask=True)
    coorx, coory = np.meshgrid(np.arange(W), np.arange(H))
    us = np_coorx2u(coorx, W)
    vs = np_coory2v(coory, H)
    zs = depth * np.sin(vs)
    cs = depth * np.cos(vs)
    xs = cs * np.sin(us)
    ys = -cs * np.cos(us)

    # Prepare points cloud
    all_xyz = np.stack([xs, ys, zs], -1).reshape(-1, 3)
    all_rgb = equirect_texture.reshape(-1, 3)
    if args.ignore_ceiling:
        mask = (~ceil_mask).reshape(-1)
        all_xyz = all_xyz[mask]
        all_rgb = all_rgb[mask]
    if args.ignore_floor:
        mask = (~floor_mask).reshape(-1)
        all_xyz = all_xyz[mask]
        all_rgb = all_rgb[mask]
    if args.ignore_wall:
        mask = (~wall_mask).reshape(-1)
        all_xyz = all_xyz[mask]
        all_rgb = all_rgb[mask]

    # Launch point cloud viewer
    pcd = open3d.geometry.PointCloud()
    pcd.points = open3d.utility.Vector3dVector(all_xyz)
    pcd.colors = open3d.utility.Vector3dVector(all_rgb)

    # Visualize result
    tobe_visualize = [pcd]
    # if not args.ignore_wireframe:
    # tobe_visualize.append(wf_line_set)

    #downpcd = open3d.geometry.PointCloud.voxel_down_sample(pcd, voxel_size = 0.003)
    #open3d.geometry.PointCloud.estimate_normals(downpcd, search_param=KDTreeSearchParamHybrid(
    #    radius=0.03, max_nn=30))
    #open3d.io.write_point_cloud("../out/downpcd.pcd", pcd)

    #open3d.io.write_point_cloud("pcdtofile.ply", pcd, write_ascii=True)
    #open3d.visualization.draw_geometries(tobe_visualize)
    #open3d.visualization.draw_geometries([downpcd])

    #tetra = open3d.geometry.TetraMesh.create_from_point_cloud(downpcd)
    #open3d.visualization.draw_geometries([tetra])

    # downpcd = open3d.geometry.PointCloud.voxel_down_sample(pcd, voxel_size=0.0001)
    pcd.estimate_normals()
    print('run Poisson surface reconstruction')
    with open3d.utility.VerbosityContextManager(
            open3d.utility.VerbosityLevel.Debug) as cm:
        mesh, densities = open3d.geometry.TriangleMesh.create_from_point_cloud_poisson(
            pcd, depth=9)
    print(mesh)
    #open3d.visualization.draw_geometries([mesh])

    densities = np.asarray(densities)
    density_colors = plt.get_cmap('plasma')(
        (densities - densities.min()) / (densities.max() - densities.min()))
    density_colors = density_colors[:, :3]
    density_mesh = open3d.geometry.TriangleMesh()
    density_mesh.vertices = mesh.vertices
    density_mesh.triangles = mesh.triangles
    density_mesh.triangle_normals = mesh.triangle_normals
    density_mesh.vertex_colors = open3d.utility.Vector3dVector(density_colors)
    #open3d.visualization.draw_geometries([density_mesh])

    print('remove low density vertices')
    vertices_to_remove = densities < np.quantile(densities, 0.01)
    mesh.remove_vertices_by_mask(vertices_to_remove)
    print(mesh)
    #open3d.visualization.draw_geometries([mesh], mesh_show_back_face=True)

    mesh.scale(5, center=mesh.get_center())
    mesh.compute_triangle_normals()
    #open3d.visualization.draw_geometries([mesh], mesh_show_back_face=True)
    open3d.io.write_triangle_mesh("meshout.stl", mesh, write_vertex_colors=True)


    sample = open3d.geometry.PointCloud()
    sample1 = [sample]
    sample1.append(wf_line_set)
    sample2 = open3d.geometry.PointCloud()
    #open3d.visualization.draw_geometries(sample1)
    #open3d.visualization.draw_geometries(tobe_visualize)


    #
    # open3d.io.write_line_set("lineset", sample1)
    #
    # # pointclouds to mesh
    # pcd.estimate_normals()
    #
    # # estimate radius for rolling ball
    # distances = pcd.compute_nearest_neighbor_distance()
    # avg_dist = np.mean(distances)
    # radius = 1.5 * avg_dist
    #
    # mesh = open3d.geometry.TriangleMesh.create_from_point_cloud_ball_pivoting(
    #     pcd, open3d.utility.DoubleVector([radius, radius * 2]))
    #
    # # create the triangular mesh with the vertices and faces from open3d
    # tri_mesh = trimesh.Trimesh(np.asarray(mesh.vertices), np.asarray(mesh.triangles),
    #                            vertex_normals=np.asarray(mesh.vertex_normals))
    #
    # print(trimesh.convex.is_convex(tri_mesh))
    #
    # #open3d.visualization.draw_geometries(tri_mesh)
    #
