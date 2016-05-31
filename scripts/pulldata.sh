#!/bin/bash

set -ex

for i in {0..280}; do
	curl -v "http://localhost:8080/spider/updatePageData?page="$i
done

