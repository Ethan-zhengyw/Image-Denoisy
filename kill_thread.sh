#!/bin/bash

pid=`ps -aux | grep java | grep Find | grep -v vim | cut -c10-14`

for id in $pid; do
    echo killing PID $id
    kill -9 $id
done
