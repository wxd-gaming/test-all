#!/bin/bash

option=""

option="${option} -H:+UnlockExperimentalVMOptions"
option="${option} -H:+ReportExceptionStackTraces"
option="${option} -H:-ParseRuntimeOptions"
option="${option} -H:ConfigurationFileDirectories=native-image/config"
option="${option} --enable-http"
option="${option} --enable-https"
option="${option} --no-fallback"
option="${option} --report-unsupported-elements-at-runtime"
option="${option} --allow-incomplete-classpath"

#option="${option} --initialize-at-build-time=ch.qos.logback.core.subst.NodeToStringTransformer"
#option="${option} --initialize-at-build-time=ch.qos.logback.core.util.StatusPrinter"

option="${option} --initialize-at-build-time=org.slf4j"
option="${option} --initialize-at-build-time=org.apache.log4j"
option="${option} --initialize-at-build-time=ch.qos.logback"
option="${option} --initialize-at-build-time=io.netty"
option="${option} --initialize-at-build-time=gvm"
option="${option} --initialize-at-build-time=com.alibaba"

#<!--build-time-->


option="${option} --initialize-at-run-time=com.lang.server.handler.FarChannelHandler"

option="${option} --initialize-at-run-time=org.slf4j.impl.StaticLoggerBinder"
option="${option} --initialize-at-run-time=io.netty"
option="${option} --initialize-at-run-time=gvm"
option="${option} --initialize-at-run-time=com.alibaba"


#<!--新增-->

#<!--trace 表示编译时 进行 跟踪，有些情况下可能会报错，比如在这里设置了A类，但是A类没有MAIN方法 会导致报错-->
#<!--trace-class-initialization=org.apache.log4j.PatternLayout-->
option="${option} --trace-class-initialization=org.apache.log4j.Layout"
option="${option} --trace-class-initialization=com.sun.beans.introspect.ClassInfo"
option="${option} --trace-class-initialization=com.sun.beans.introspect.MethodInfo"
option="${option} --trace-class-initialization=com.sun.beans.TypeResolver"
option="${option} --trace-class-initialization=java.beans.Introspector"
option="${option} --trace-class-initialization=java.beans.ThreadGroupContext"
option="${option} --trace-class-initialization=ch.qos.logback"
option="${option} --trace-class-initialization=gvm"
option="${option} --trace-class-initialization=com.alibaba"


option="${option} -Dio.netty.tryReflectionSetAccessible=true"
option="${option} --add-exports=java.base/java.nio=ALL-UNNAMED"
option="${option} --add-opens java.base/java.nio=ALL-UNNAMED"

/usr/local/graalvm-jdk-17.0.11+7.1/bin/native-image $option -cp "c/target/lib/*" -jar c/target/server-boot.jar native-image/graalvm-test
#rm -rfv lib/gvm.*-1.0-SNAPSHOT.jar

./native-image/graalvm-test