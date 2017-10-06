# Copyright 2017-present Facebook. All Rights Reserved.

SUMMARY = "Firmware Utility"
DESCRIPTION = "Utility to upgrade firmware and to display firmware version of various components in the system"
SECTION = "base"
PR = "r1"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://fw-util.cpp;beginline=4;endline=16;md5=5f8ba3cd0f216026550dbcc0186d5599"

SRC_URI =+ "file://Makefile \
           file://fw-util.h \
           file://fw-util.cpp \
           file://bmc.cpp \
           file://nic.cpp \
           file://fscd.cpp \
          "

S = "${WORKDIR}"

LDFLAGS =+ " -lpthread -ljansson -lpal "
DEPENDS += "jansson libpal"
RDEPENDS_${PN} += "jansson libpal"
CXXFLAGS += '-DVERIFIED_BOOT=${@bb.utils.contains("IMAGE_FEATURES", "verified-boot", "1", "0", d)}'

do_install() {
  install -d ${D}${bindir}
  install -m 0755 fw-util ${D}${bindir}/fw-util
}

FILES_${PN} = "${prefix}/bin"