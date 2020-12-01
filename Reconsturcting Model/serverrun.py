import werkzeug
from flask import Flask, request, jsonify, flash, redirect, url_for
import flask

app = Flask(__name__)

#@app.route('/api/postname', methods = ['GET', 'POST'])
"""#@app.route('/')
def handle_request():
    print("receive")
    json = request.get_json()
    print("receive")
    print(json)

    with open('C:\\Git\\JJinBang\\test.json', 'w', encoding='utf-8') as make_file:
        json(make_file, indent="\t")"""


@app.route('/api/postname', methods = ['GET', 'POST'])
def handle_request():
    print("receive")
    if request.method == 'POST':
        print("receive")
        f = request.files['image']
        print(f)
        f = request.get_json()
        print(f)
        #print(image)
        # 저장할 경로 + 파일명
        print("receive")
        f.save('ffilename')
        print("receive")
        return 'uploads 디렉토리 -> 파일 업로드 성공!'





# GET
@app.route('/users/<user>')
def hello_user(user):
    """
    this serves as a demo purpose
    :param user:
    :return: str
    """
    return "Hello %s!" % user

# POST
"""@app.route('/api/postName', methods=['POST'])
def get_text_prediction():
    
    #predicts requested text whether it is ham or spam
    #:return: json
    
    json = request.get_json()
    print(json)
    if len(json['name']) == 0:
        return jsonify({'error': 'invalid input'})

    return jsonify({'you sent this': json['name']})"""

app.run(host="218.155.251.115", port=5000)