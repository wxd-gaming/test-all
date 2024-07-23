@echo off
C:\\java\\graalvm-jdk-21.0.3+7.1\\bin\\java -agentlib:native-image-agent=config-merge-dir=graalvm-win\\native-image\\config -cp "target/mysql-server.jar;target/lib/*" example.db.Main