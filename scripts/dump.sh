#!/bin/bash

DBHOST=localhost
DBUSER=root
DBPWD=11111111
DBNAME=house

mysqldump -d -h $DBHOST -u $DBUSER -p$DBPWD $DBNAME project_basic_data > ./house_project_basic_data.sql
mysqldump -d -h $DBHOST -u $DBUSER -p$DBPWD $DBNAME pre_sell_license_data > ./house_pre_sell_license_data.sql
mysqldump -d -h $DBHOST -u $DBUSER -p$DBPWD $DBNAME earth_basic_data > ./house_earth_basic_data.sql
mysqldump -d -h $DBHOST -u $DBUSER -p$DBPWD $DBNAME project_tag > ./house_project_tag.sql
