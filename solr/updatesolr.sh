#!/bin/bash

# put this to crontab
#*/1 * * * * /home/guo/updatesolr.sh

curl 'http://localhost:8983/solr/collection1/dataimport' -H 'Origin: http://localhost:8983' -H 'Accept-Encoding: gzip, deflate' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/50.0.2661.102 Chrome/50.0.2661.102 Safari/537.36' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Accept: application/json, text/javascript, */*; q=0.01' -H 'Referer: http://localhost:8983/solr/' -H 'X-Requested-With: XMLHttpRequest' -H 'Connection: keep-alive' --data 'command=delta-import&commit=true&wt=json&indent=true&entity=project_basic_data&verbose=false&clean=false&optimize=false&debug=false' --compressed

# must sleep the est. time the delta import may cost
sleep 5

curl 'http://localhost:8983/solr/collection1/dataimport' -H 'Origin: http://localhost:8983' -H 'Accept-Encoding: gzip, deflate' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/50.0.2661.102 Chrome/50.0.2661.102 Safari/537.36' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Accept: application/json, text/javascript, */*; q=0.01' -H 'Referer: http://localhost:8983/solr/' -H 'X-Requested-With: XMLHttpRequest' -H 'Connection: keep-alive' --data 'command=delta-import&commit=true&wt=json&indent=true&entity=pre_sell_license_data&verbose=false&clean=false&optimize=false&debug=false' --compressed
sleep 5

curl 'http://localhost:8983/solr/collection1/dataimport' -H 'Origin: http://localhost:8983' -H 'Accept-Encoding: gzip, deflate' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/50.0.2661.102 Chrome/50.0.2661.102 Safari/537.36' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Accept: application/json, text/javascript, */*; q=0.01' -H 'Referer: http://localhost:8983/solr/' -H 'X-Requested-With: XMLHttpRequest' -H 'Connection: keep-alive' --data 'command=delta-import&commit=true&wt=json&indent=true&entity=earth_basic_data&verbose=false&clean=false&optimize=false&debug=false' --compressed
sleep 5

curl 'http://localhost:8983/solr/collection1/dataimport' -H 'Origin: http://localhost:8983' -H 'Accept-Encoding: gzip, deflate' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/50.0.2661.102 Chrome/50.0.2661.102 Safari/537.36' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Accept: application/json, text/javascript, */*; q=0.01' -H 'Referer: http://localhost:8983/solr/' -H 'X-Requested-With: XMLHttpRequest' -H 'Connection: keep-alive' --data 'command=delta-import&commit=true&wt=json&indent=true&entity=project_tag&verbose=false&clean=false&optimize=false&debug=false' --compressed
