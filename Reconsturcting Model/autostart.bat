python preprocess.py --img_glob Demo/demo.jpg --output_dir Demo/preprocessed/
python inference.py --pth ckpt/resnet50_rnn__mp3d.pth --img_glob Demo/preprocessed/demo_aligned_rgb.png --output_dir Demo/inferenced --visualize
python layout_viewer.py --img Demo/preprocessed/demo_aligned_rgb.png --layout Demo/inferenced/demo_aligned_rgb.json --ignore_ceiling
pause