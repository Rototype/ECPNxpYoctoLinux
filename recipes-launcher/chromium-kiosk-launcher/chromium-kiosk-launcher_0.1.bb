SUMMARY = "Chromium launcher"
DESCRIPTION = "Add a launcher to the weston desktop to launch chromium in kiosk mode"
LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "\
    file://icon_chromium_launcher.png \
    file://chromium.sh \
"
do_install() {
        # copy the icon and the shell command on the target
        install -d ${D}${datadir}/chromium_launcher
        install -m 644  ${WORKDIR}/icon_chromium_launcher.png    ${D}${datadir}/chromium_launcher/icon_chromium_launcher.png
        install -m 755  ${WORKDIR}/chromium.sh    ${D}${datadir}/chromium_launcher/chromium.sh
}
FILES_${PN} += "${datadir}/chromium_launcher"

 