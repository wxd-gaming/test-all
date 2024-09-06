export JAVA_HOME=/usr/local/jdk-1.8.0_301;
export JRE_HOME=${JAVA_HOME}/jre;
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib;
export PATH=${JAVA_HOME}/bin:${PATH};
java -version

mvn clean package -Dmaven.test.skip=true -pl assist -am
if [ $? != 0 ]; then
  echo "打包失败"
  exit 1
fi

cd assist/target

java -javaagent:wxdgaming-assist.jar -cp "wxdgaming-assist.jar:lib/*" agent.assist.AssistMain
java -javaagent:wxdgaming-assist.jar -cp "wxdgaming-assist.jar:lib/*" agent.bytebuddy.ByteBuddyExample