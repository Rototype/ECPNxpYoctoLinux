#!/bin/bash

file_cmn="/home/root/update/Comunicator"

if [ -f "$file_cmn" ] 
then
	echo "    update Comunicator.."
	cp $file_cmn /home/root/app/Comunicator
fi


