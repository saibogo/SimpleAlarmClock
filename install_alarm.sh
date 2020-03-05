#! /usr/bin/sh
mkdir build
cd ./build || exit
rm -R *
cd ../src || exit
javac -verbose -d ../build Main.java
cp main/resources/electra.ttf ~/.fonts
cd ..
command1='cd '`pwd`/build'; '
command2='sh alarmClock.sh'
fullCommand='alias alarmClock="'$command1$command2'"'
echo $fullCommand >> ~/.bashrc
