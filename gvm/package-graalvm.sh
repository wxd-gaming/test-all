#!/bin/bash

# 设置JAVA_HOME环境变量
export JAVA_HOME=/usr/local/graalvm-jdk-21.0.3+7.1
# 将JAVA_HOME加入到PATH变量中
export PATH=$JAVA_HOME/bin:$PATH
# 打印出Java版本信息以验证设置是否成功
java -version
#cd /mnt/e/work/engine712-server
mvn clean package -f pom.xml

cd c/target
pwd

#/usr/local/graalvm-jdk-21.0.3+7.1/bin/java -agentlib:native-image-agent=config-merge-dir=META-INF/native-image -classpath .:lib/*:server-boot.jar gvm.c.Main
/usr/local/graalvm-jdk-21.0.3+7.1/bin/java -agentlib:native-image-agent=config-merge-dir=META-INF/native-image -jar server-boot.jar
option="--enable-url-protocols=http --enable-http --enable-https --no-fallback --report-unsupported-elements-at-runtime --allow-incomplete-classpath -H:+ReportExceptionStackTraces -H:-ParseRuntimeOptions"
/usr/local/graalvm-jdk-21.0.3+7.1/bin/native-image $option -jar server-boot.jar graalvm-test
#rm -rfv lib/gvm.*-1.0-SNAPSHOT.jar

./graalvm-test