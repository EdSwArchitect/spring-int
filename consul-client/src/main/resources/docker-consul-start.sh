#!/bin/bash

# /etc/consul.d contains the real configuration for Consul
# -v <host dir>:<container dir>

docker run --name consul \
 -p 8300:8300 \
 -p 8301:8301 \
 -p 8301:8301/udp \
 -p 8302:8302 \
 -p 8302:8302/udp \
 -p 8400:8400 \
 -p 8500:8500 \
 -d -v /etc/consul.d:/etc/consul.d \
 progrium/consul -server \
 -config-dir=/etc/consul.d -bootstrap-expect=1  
