import sys
import json
from pprint import pprint
from elasticsearch import Elasticsearch
es = Elasticsearch([{'host': 'localhost','port': '9200'}])
MyFile= open("C:\\Users\\alay.singhal\\Downloads\\File conversion project\\After Markings1\\D0EvisionMetaData.json","r").read()
#for line in MyFile:
#    print(line)
#MyFile.close()

ClearData = MyFile.splitlines(True)
i=0
json_str=""
docs ={}
for line in ClearData:
    line = ''.join(line.split())
    if line != "},":
        json_str = json_str+line
    else:
        docs[i]=json_str+"}"
        json_str=""
        print(docs[i])
        es.index(index='fileinfo-markeddata1111', doc_type='Blog', id=i, body=docs[i])
        i=i+1 