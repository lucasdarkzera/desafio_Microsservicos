#!/bin/bash
# first grab all dependencies
mvn dependency:resolve >/dev/null

# then list them with -o to keep noise low,
# remove extra information and duplicates
mvn -o dependency:list \
| grep ":.*:.*:.*" \
| cut -d] -f2- \
| sed 's/:[a-z]*$//g' \
| sort -u 
