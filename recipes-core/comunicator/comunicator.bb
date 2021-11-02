#
# Comunicator installation recipe (not compiled here)
#

SUMMARY = "Web-socket service simple installation"
SECTION = "ROTOTYPE/apps"
LICENSE = "CLOSED"


DEPENDS = "sqlite3"




SRC_URI += "file://99-rototype_init.sh"
SRC_URI += "file://startEcpApp.sh"
SRC_URI += "file://Comunicator"
SRC_URI += "file://impianto.conf"

S = "${WORKDIR}"

do_install() {
	     install -d ${D}/home/root/
	     install -d ${D}/home/root/script
	     install -d ${D}/home/root/app
	     install -d ${D}/home/root/app/etc
	     install -d ${D}/home/root/startup

	     install -m 0777 ${S}/99-rototype_init.sh ${D}/home/root/startup
	     install -m 0777 ${S}/startEcpApp.sh ${D}/home/root/script
	     install -m 0777 ${S}/Comunicator ${D}/home/root/app
	     install -m 0444 ${S}/impianto.conf ${D}/home/root/app/etc
}

FILES_${PN} += "/home/root/"
FILES_${PN} += "/home/root/script"
FILES_${PN} += "/home/root/app"
FILES_${PN} += "/home/root/startup"
FILES_${PN} += "/home/root/app/etc"

RDEPENDS_${PN} += "bash"


 
