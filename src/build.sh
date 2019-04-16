#!/bin/bash
echo "Builing Project: hexalpha"

function build() {
  echo "Building: $1"
  if [ ! -f $1*.class ]; then
    rm $1*.class
  fi
  javac $1*.java
}


build hexalpha/
build hexalpha/controller/
build hexalpha/controller/splash/
build hexalpha/controller/game/
build hexalpha/controller/winner/
build hexalpha/engine/
