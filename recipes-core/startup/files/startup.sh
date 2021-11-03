#!/bin/sh
echo 30000 >  /proc/sys/vm/min_free_kbytes

SCRIPTDIR=/home/root/startup
for SCRIPT in $SCRIPTDIR/*.sh ; do
        logger -t "Startup Script" "execute $SCRIPT"
        sh $SCRIPT
done



