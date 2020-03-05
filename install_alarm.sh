#! /usr/bin/sh
mkdir build
cd ./build || exit
rm -R *
cd ../src || exit
javac -verbose -d ../build Main.java
cp main/resources/electra.ttf ~/.fonts
cd ..
cp alarmClock.sh ./build/alarmClock.sh
command1='cd '`pwd`/build'; '
command2='java Main $@'
fullCommand='alias alarmClock="'$command1$command2'"'
echo $fullCommand >> ~/.bashrc
source ~/.bashrc

