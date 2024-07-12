#!/bin/bash

# 设置JAVA_HOME环境变量
#export JAVA_HOME=/usr/local/graalvm-jdk-17.0.11+7.1
export JAVA_HOME=/usr/local/jdk-1.8.0_301
# 将JAVA_HOME加入到PATH变量中
export PATH=$JAVA_HOME/bin:$PATH
# 打印出Java版本信息以验证设置是否成功
java -version
#cd /mnt/e/work/engine712-server
mvn clean package -f pom.xml

#/usr/local/graalvm-jdk-21.0.3+7.1/bin/java -agentlib:native-image-agent=config-merge-dir=META-INF/native-image -classpath .:lib/*:server-boot.jar gvm.c.Main
/usr/local/graalvm-jdk-17.0.11+7.1/bin/java -agentlib:native-image-agent=config-merge-dir=native-image/config -cp "c/target/lib/*:c/target/server-boot.jar" gvm.test.c.NativeMain