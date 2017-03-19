#!/usr/bin/env bash

######################################
## Gossip server bootstrap snippet  ##
##                                  ##
## Author: syhily@gmail.com         ##
######################################

# Don't edit this file if you don't understand
# Configurable paras
MAIN_CLASS="me.yufan.gossip.Gossip"

BASE_DIR=$(dirname $0)/..

if [ "x${CONFIG_DIR}" = "x" ]; then
    CONFIG_DIR=$BASE_DIR/config/
fi

# Create logs directory
if [ "x${LOG_DIR}" = "x" ]; then
    LOG_DIR="$BASE_DIR/logs"
fi

if [ ! -d "${LOG_DIR}" ]; then
    mkdir -p "${LOG_DIR}"
fi

# Memory options
if [ -z "${HEAP_OPTS}" ]; then
    HEAP_OPTS="-Xms100m -Xmx300m -XX:PermSize=64m"
fi

# Debug for GC
if [ "x${GOSSIP_DEBUG}" != "x" ]; then
    HEAP_OPTS="${HEAP_OPTS} -verbose:gc"
fi

# Which java to use
if [ -z "${JAVA_HOME}" ]; then
    echo -e "No \033[41;37m\$JAVA_HOME\033[0m defined, would try to find java in your \033[41;37m\$PATH\033[0m.\nIf this fails, trying define \033[41;37m\$JAVA_HOME\033[0m"
    JAVA="java"
else
    JAVA="${JAVA_HOME}/bin/java"
fi

# JVM performance options
if [ -z "${JVM_PERFORMANCE_OPTS}" ]; then
    JVM_PERFORMANCE_OPTS="-server -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+CMSScavengeBeforeRemark"
fi

# Classpath addition for release
for file in ${BASE_DIR}/lib/*.jar;
do
    CLASSPATH=${CLASSPATH}:${file}
done

# Start the gossip server
${JAVA} ${HEAP_OPTS} ${JVM_PERFORMANCE_OPTS} -cp ${CLASSPATH} ${MAIN_CLASS} --config ${CONFIG_DIR} $@
