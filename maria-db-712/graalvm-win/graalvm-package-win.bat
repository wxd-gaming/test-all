@echo off
C:\\java\\graalvm-jdk-21.0.3+7.1\\bin\\java -agentlib:native-image-agent=config-merge-dir=target/winfm/config -Dlogback.configurationFile=logback-gbk.xml -cp "target/winfm/mysql-server.jar;target/winfm/lib/*" db712.winfm.ApplicationMain 1