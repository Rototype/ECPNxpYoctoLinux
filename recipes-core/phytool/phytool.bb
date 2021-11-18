SUMMARY = "PHY interface tool for Linux"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PV = "1.0.1"
SRCREV = "1a3ea62a218206e9faf3b27fb5d01c85692024c8"
SRC_URI = "git://github.com/wkz/phytool.git"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${prefix}/bin
	oe_runmake 'DESTDIR=${D}' 'PREFIX=${prefix}' install
}
