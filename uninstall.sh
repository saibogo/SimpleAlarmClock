#! /usr/bin/sh

sed -i '/alarmClock/d' ~/.bashrc
rm -r ./build
rm ~/.local/share/applications/alarmClock.desktop
rm ~/.fonts/electra.ttf
rm ~/.local/share/icons/alarmClock.png
