# ECP Platform Yocto Recipe for building Linux image
## OS

Download and install Ubuntu 18.04

## Preparation of environment


    sudo apt update
    sudo apt upgrade
    sudo apt install git gitk build-essential vim curl wget software-properties-common apt-transport-https ca-certificates
    sudo apt install binfmt-support qemu qemu-user-static debootstrap debianutils kpartx gpart lvm2 \
    dosfstools binutils gawk git-core diffstat unzip texinfo chrpath socat autoconf automake xterm \
    sed cvs subversion coreutils texi2html bc docbook-utils help2man make gcc g++ gcc-multilib \
    desktop-file-utils libtool libglib2.0-dev libarchive-dev lib32ncurses5-dev libsdl1.2-dev \
    libegl1-mesa libgl1-mesa-dev libglu1-mesa-dev mercurial groff asciidoc u-boot-tools mtd-utils \
    device-tree-compiler ccrypt xz-utils cpio iputils-ping python python-git python-m2crypto \
    python-pysqlite2 python3 python3-pip python3-pexpect python3-git python3-jinja2 \
    bison flex libssl-dev kmod fakeroot lzop

    git config --global user.email "<your mail>"
    git config --global user.name "<your name>"

    mkdir -p ~/bin
    curl http://commondatastorage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
    chmod a+x ~/bin/repo
    PATH=${PATH}:~/bin

## Download Freescale project

    mkdir -p ~/imx-yocto-bsp
    cd ~/imx-yocto-bsp
    repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-hardknott -m imx-5.10.35-2.0.0.xml
    repo sync -j10

## Download Rototype project

    mkdir -p ~/imx-yocto-bsp/sources
    cd ~/imx-yocto-bsp/sources
    git clone git://github.com/Rototype/ECPNxpYoctoLinux meta-rototype-ecp

## Build first image

    cd ~/imx-yocto-bsp
    DISTRO=ecp-imx-wayland MACHINE=imx8mq-ecp source imx-setup-release.sh -b build-rototype-ecp
    bitbake-layers add-layer ../sources/meta-rototype-ecp
    bitbake rototype-evaluation-image

    cd ~/imx-yocto-bsp
    source setup-environment build-rototype-ecp

## Build next images

    bitbake rototype-evaluation-image
    
## Copy image to SD card

    sudo bmaptool copy ~/imx-yocto-bsp/build-rototype-ecp/tmp/deploy/images/imx8mq-ecp/rototype-evaluation-image-imx8mq-ecp.wic.bz2 /dev/<sdcarddevicename> (example /dev/sdc)

## Copy installation/upgrading material to destination folder

    mkdir -p ~/imx-yocto-bsp/update (use your preferred folder)
    cp ~/imx-yocto-bsp/build-rototype-ecp/tmp/deploy/images/imx8mq-ecp/rototype-evaluation-image-imx8mq-ecp.tar.bz2 ~/imx-yocto-bsp/update/rootfs.tar.bz2
    cp ~/imx-yocto-bsp/build-rototype-ecp/tmp/deploy/images/imx8mq-ecp/Image ~/imx-yocto-bsp/update/Image.bin
    cp ~/imx-yocto-bsp/build-rototype-ecp/tmp/deploy/images/imx8mq-ecp/imx8mq-ecp.dtb ~/imx-yocto-bsp/update/imx8mq-ecp.dtb
    cp ~/imx-yocto-bsp/build-rototype-ecp/tmp/deploy/images/imx8mq-ecp/imx-boot ~/imx-yocto-bsp/update/imx-boot.bin