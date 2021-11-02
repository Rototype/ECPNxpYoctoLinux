SUMMARY = "Webkiosk launcher"
DESCRIPTION = "Add a launcher to the weston desktop to launch the qt-kiosk-browser"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "\
    file://icon_chromium_launcher.png \
    file://webkiosk.sh \
"
do_install() {
        # copy the icon and the shell command on the target
        install -d ${D}${datadir}/webkiosk_launcher
        install -m 644  ${WORKDIR}/icon_chromium_launcher.png    ${D}${datadir}/webkiosk_launcher/icon_chromium_launcher.png
        install -m 755  ${WORKDIR}/webkiosk.sh    ${D}${datadir}/webkiosk_launcher/webkiosk.sh
}
FILES_${PN} += "${datadir}/webkiosk_launcher"

 