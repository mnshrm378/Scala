import sys
import json
#from pprint import pprint
#from elasticsearch import Elasticsearch
#es = Elasticsearch([{'host': 'localhost','port': '9200'}])

MyFile1= open("C:\\Users\\alay.singhal\\Downloads\\File conversion project\\SMPC522A1DC0032_DE-PIAE1004_20170317_141659_FileInfo\\visionMetaData.json","r")
x = MyFile1.readlines()
print(x)
MyFile1.close()

#ClearData = MyFile.splitlines(True)
#i=0
#json_str=""
#docs ={}
#for line in ClearData:
#    line = ''.join(line.split())
#    if line != "},":
#        json_str = json_str+line
#    else:
#        docs[i]=json_str+"}"
#        json_str=""
#        print(docs[i])
#        es.index(index='fileinfo-markeddata1111', doc_type='Blog', id=i, body=docs[i])
#        i=i+1 