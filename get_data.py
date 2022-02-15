import os
from subprocess import PIPE, Popen
import requests
import sys
from datetime import datetime, timedelta
# define path to saved file
file_name = f"comptages_routiers_permanents_{sys.argv[1]}.csv"
date_next = datetime.strptime(sys.argv[1], '%Y-%m-%d' )
date_next += timedelta(days=1)
url = f'https://opendata.paris.fr/api/v2/catalog/datasets/comptages-routiers-permanents/exports/csv/?' \
      f'where=t_1h>"{sys.argv[1]}T00:00:00+00:00"&' \
      f'where=t_1h<"{date_next.strftime("%Y-%m-%d")}T00:00:00+00:00"'

reponse = requests.get(url)

url_content = reponse.content
csv_file = open(file_name, 'wb')

csv_file.write(url_content)
csv_file.close()

# create path to your username on hdfs
hdfs_path = os.path.join(os.sep, 'data', 'groupe20', "comptages", file_name)
# put csv into hdfs
put = Popen(["hadoop", "fs", "-put", file_name, hdfs_path], stdin=PIPE, bufsize=-1)
put.communicate()
