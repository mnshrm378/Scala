import json , os, requests
from elasticsearch import Elasticsearch
directory = 'C:\\Users\\alay.singhal\\Downloads\\File conversion project\\SMPC5'
res = requests.get('http://localhost:9200')
print(res.content)
es = Elasticsearch([{'host': 'localhost','port': '9200'}])
i = 0
for filename in os.listdir(directory):
        if filename.endswith(".json"): 
                   f = open(directory + "\\" + filename)
                   x = directory + "\\" + filename
                   docket_content = f.read()
                   es.index(index='fileinfo-markeddata', doc_type='docket', id=x, body=json.loads(docket_content))
                   i = i+1