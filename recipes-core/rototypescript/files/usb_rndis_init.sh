#!/bin/bash

echo "    Ethernet Gadget Init"

modprobe g_ether

sleep 2

ifconfig  usb0 192.168.2.100 up

