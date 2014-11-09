#!/bin/bash

h=0.1; b=0.1; n=0.1;
H=0.5; B=10;  N=10;
step=0.1

while [ `echo "$h < $H" | bc` -eq 1 ]
do
    {
        echo "Start thread: h = $h"
        java -classpath bin FindBetterParam $h $b $n $H $B $N $step &
        h=$(echo "$h + $step" | bc)
    }
done
wait
