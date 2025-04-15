#!/bin/sh

for i in $(seq 1 1000);
do
  curl https://localhost:8443/hello -k &
  # echo $i
done
