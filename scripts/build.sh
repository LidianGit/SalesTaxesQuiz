#!/bin/bash

DIR="$( dirname $0 )"
ROOT_DIR="$DIR/.."

#default
mvn_targets="clean install"

mvn $mvn_targets -P bootApplication