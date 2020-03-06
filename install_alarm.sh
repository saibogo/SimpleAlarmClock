#! /usr/bin/sh

if javac --version
    
then
    echo 'Found jdk. Start compiling classes in ./src'
    mkdir build
    cd ./build || exit
    rm -R *
    cd ../src || exit
    javac -verbose -d ../build Main.java
    cp main/resources/electra.ttf ~/.fonts
    cd ..
    command1='java -classpath '`pwd`/build' Main $@'
    fullCommand='alias alarmClock="'$command1'"'
    echo $fullCommand
    echo $fullCommand >> ~/.bashrc
    . ~/.bashrc


    desktopFile=~/.local/share/applications/alarmClock.desktop
    if find ~/.local/share/applications/ -name alarmClock.desktop
        then 
            echo $desktopFile" Found. Removing it."
            rm $desktopFile
    fi
    echo $desktopFile" Create"
    touch $desktopFile
    echo '[Desktop Entry]' >> $desktopFile
    echo 'Type=Application' >> $desktopFile
    echo 'Encoding=UTF-8' >> $desktopFile
    echo 'Name=Simple Alarm Clock' >> $desktopFile
    echo 'Comment=Alarm clock, Timer and Stop Watch' >> $desktopFile
    echo 'Exec=java -classpath '`pwd`'/build Main' >> $desktopFile
    echo 'Terminal=false' >> $desktopFile


else
    echo 'Not found jdk. Please install and try again!'
    exit 1
fi
