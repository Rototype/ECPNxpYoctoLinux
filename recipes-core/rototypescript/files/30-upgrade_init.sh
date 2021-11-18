#!/bin/bash

echo "    ECP Platform Update>"

usb_device="/dev/sda"
file_prboard="/home/root/update/_program"
file_prusb="/run/media/sda/_program"
file_update="/home/root/update/_update"
file_update_usb="/run/media/sda/_update"

if [ -b "${usb_device}1" ]
then
    usb_device="${usb_device}1"
    mkdir -p /run/media/sda
    mount $usb_device /run/media/sda
elif [ -b "${usb_device}" ]
then
    mkdir -p /run/media/sda
    mount $usb_device /run/media/sda
fi

if [ -f "$file_prusb" ]
then
        umount /run/media/sda
        mount $usb_device /home/root/update
        systemctl stop weston
        psplash -n &
        psplash-write "ECP SYSTEM PROGRAMMING......"
        sleep 1.5
        psplash-write "MSG USB_PROGRAMMING_FOUND.."
        psplash-write "PROGRESS 10"
        /home/root/script/prEMMC.sh
        psplash-write "PROGRESS 40"
        /home/root/script/prI210.sh
        psplash-write "PROGRESS 50"
        /home/root/script/prFpga.sh
        psplash-write "PROGRESS 60"
        /home/root/script/prSoc.sh
        psplash-write "PROGRESS 100"
        #killall psplash
        systemctl start weston
elif [ -f "$file_prboard" ]
then
        systemctl stop weston
        psplash -n &
        psplash-write "ECP SYSTEM PROGRAMMING......"
        sleep 1.5
        psplash-write "MSG SD_PROGRAMMING_FOUND.."
        psplash-write "PROGRESS 10"
        /home/root/script/prEMMC.sh
        psplash-write "PROGRESS 40"
        /home/root/script/prI210.sh
        psplash-write "PROGRESS 50"
        /home/root/script/prFpga.sh
        psplash-write "PROGRESS 60"
        /home/root/script/prSoc.sh
        psplash-write "PROGRESS 100"
        #killall psplash
        systemctl start weston
fi

if [ -f "$file_update" ]
then
        echo "    ECP APP UPDATE FOUND............................>"
        /home/root/script/prKernel.sh
        /home/root/script/prDevicetree.sh
        /home/root/script/prComunicator.sh
        /home/root/script/prFpga.sh
        /home/root/script/prSoc.sh
        rm $file_update
fi

if [ -f "$file_update_usb" ]
then
        echo "    ECP USB UPDATE FOUND............................>"
        umount /run/media/sda
        mount $usb_device /home/root/update
        /home/root/script/prKernel.sh
        /home/root/script/prDevicetree.sh
        /home/root/script/prComunicator.sh
        /home/root/script/prFpga.sh
        /home/root/script/prSoc.sh
fi

umount /run/media/sda
umount /home/root/update
