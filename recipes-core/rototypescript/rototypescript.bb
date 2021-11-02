#
# This file is the script recipe.
#

SUMMARY = "Programming script application"
SECTION = "ROTOTYPE/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INHIBIT_PACKAGE_STRIP = "1"

SRC_URI = "file://prEcp.sh"
SRC_URI += "file://mksdcard.sh"
SRC_URI += "file://prEMMC.sh"
SRC_URI += "file://prKernel.sh"
SRC_URI += "file://prDevicetree.sh"
SRC_URI += "file://prSoc.sh"
SRC_URI += "file://prFpga.sh"
SRC_URI += "file://10-gpio_init.sh"
SRC_URI += "file://11-fpga_init.sh"
SRC_URI += "file://20-network_init.sh"
SRC_URI += "file://21-eth0_init.sh"
SRC_URI += "file://22-eth1_init.sh"
SRC_URI += "file://23-usb_rndis_init.sh"
SRC_URI += "file://30-upgrade_init.sh"
SRC_URI += "file://prI210.sh"
SRC_URI += "file://usb_gadget_init.sh"
SRC_URI += "file://eth0.param"
SRC_URI += "file://rc.local"
SRC_URI += "file://I210_Invm_Copper_NoAPM_v0.6.HEX"
SRC_URI += "file://resetFpga.sh"
SRC_URI += "file://resetSoc.sh"
SRC_URI += "file://prComunicator.sh"


S = "${WORKDIR}"

do_install() {
	     install -d ${D}/home/root/script
	     install -d ${D}/home/root/config
	     install -d ${D}/home/root/firmware
	     install -d ${D}/home/root/update
	     install -d ${D}/home/root/startup
	     install -d ${D}${sysconfdir}	
	     install -m 0777 ${S}/rc.local ${D}${sysconfdir}
	     install -m 0777 ${S}/10-gpio_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/11-fpga_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/20-network_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/21-eth0_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/22-eth1_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/23-usb_rndis_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/30-upgrade_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/mksdcard.sh ${D}/home/root/script
	     install -m 0777 ${S}/prEcp.sh ${D}/home/root/script
	     install -m 0777 ${S}/prEMMC.sh ${D}/home/root/script
	     install -m 0777 ${S}/prKernel.sh ${D}/home/root/script
	     install -m 0777 ${S}/prDevicetree.sh ${D}/home/root/script
	     install -m 0777 ${S}/prSoc.sh ${D}/home/root/script
	     install -m 0777 ${S}/prFpga.sh ${D}/home/root/script
	     install -m 0777 ${S}/prI210.sh ${D}/home/root/script
	     install -m 0777 ${S}/usb_gadget_init.sh ${D}/home/root/script
	     install -m 0777 ${S}/eth0.param ${D}/home/root/config
	     install -m 0777 ${S}/I210_Invm_Copper_NoAPM_v0.6.HEX ${D}/home/root/firmware
	     install -m 0777 ${S}/resetFpga.sh ${D}/home/root/script
	     install -m 0777 ${S}/resetSoc.sh ${D}/home/root/script
	     install -m 0777 ${S}/prComunicator.sh ${D}/home/root/script
}

FILES_${PN} += "/home/root/script"
FILES_${PN} += "/home/root/firmware"
FILES_${PN} += "/home/root/config"
FILES_${PN} += "/home/root/update"
FILES_${PN} += "/home/root/"
FILES_${PN} += "/etc/"

RDEPENDS_${PN} += "bash"


