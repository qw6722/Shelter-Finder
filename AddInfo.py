import pandas as pd
import pyrebase
import csv
import json

config = {
  "apiKey": "AIzaSyBOZISgkveD5SXntkJ3oCRn_e7jo-Wp9pM",
  "authDomain": "shelterfinder.firebaseapp.com",
  "databaseURL": "https://shelterfinder-6d316.firebaseio.com/",
  "storageBucket": "shelterfinder.appspot.com"
}

firebase = pyrebase.initialize_app(config)

with open('Homeless Shelter Database.csv') as f:
	for row in csv.DictReader(f):
		json_data = json.dumps(row)
		firebase.database().child("Data").push(json.loads(json_data))

