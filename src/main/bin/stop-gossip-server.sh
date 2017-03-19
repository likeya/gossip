#!/usr/bin/env bash

######################################
## Gossip server bootstrap snippet  ##
##                                  ##
## Author: syhily@gmail.com         ##
######################################

# Don't edit this file if you don't understand
GOSSIP_PROCESS_NAME="Gossip"

# Which jps to use
JPS_EXECUTABLE_PATH=""

if [ -z "${JAVA_HOME}" ]; then
    echo -e "No \033[41;37m\$JAVA_HOME\033[0m defined, would try to find java in your \033[41;37m\$PATH\033[0m.\nIf this fails, trying define \033[41;37m\$JAVA_HOME\033[0m"
    JPS_EXECUTABLE_PATH="JPS"
else
    JPS_EXECUTABLE_PATH="${JAVA_HOME}/bin/java"
fi

echo "Trying to find the gossip process.."

PID=`jps | grep ${GOSSIP_PROCESS_NAME} | cut -d ' ' -f 1`

if [ -z "${PID}" ]; then
    echo "Gossip server is already stopped, nothing to do, exit."
else
    echo "Stop Gossip server, PID: ${PID}"
    kill -9 ${PID}
fi
