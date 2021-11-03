SUMMARY = "Add startup script to the rootfs"
DESCRIPTION = "add startup Script, started as a systemd service"
LICENSE = "CLOSED"

SRC_URI =  " \
    file://startup.sh \
    file://startup.service \
"

SYSTEMD_SERVICE_${PN} = "startup.service"
RDEPENDS_${PN} = "systemd"

do_compile () {
}

do_install () {
    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/*.sh ${D}/${sbindir}

    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/startup.service ${D}${sysconfdir}/systemd/system

    # enable startup service
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -sf ${D}${sysconfdir}/systemd/system/startup.service \
           ${D}${sysconfdir}/systemd/system/multi-user.target.wants/startup.service

}

NATIVE_SYSTEMD_SUPPORT = "1"
inherit allarch systemd
