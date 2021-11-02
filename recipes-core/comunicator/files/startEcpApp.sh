#!/bin/bash
echo "Launching Comunicator"
cd /home/root/app

# nohup without logs
nohup /home/root/app/Comunicator > /dev/null 2>&1 &

# nohup.out with logs for debugging
#nohup /home/root/app/Comunicator &

# Alternative solution
#sleep 10
#/usr/bin/openvt -c5 -f -- /home/root/app/Comunicator

# Simple launch without log
#/home/root/app/Comunicator &

# Simple launch with log
#/home/root/app/Comunicator >> comunicatorlog.txt 2>> comunicatorlog.txt &


