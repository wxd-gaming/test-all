#!/bin/bash

# 设置JAVA_HOME环境变量
export JAVA_HOME=/usr/local/jdk-21
# 将JAVA_HOME加入到PATH变量中
export PATH=$JAVA_HOME/bin:$PATH
# 打印出Java版本信息以验证设置是否成功
java -version
#cd /mnt/e/work/engine712-server
mvn clean package -f pom.xml

cd c/target

/usr/local/jdk-21/bin/java -javaagent:server-boot-encrypted.jar -jar server-boot-encrypted.jar