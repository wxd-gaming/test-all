package code.impl;

import org.junit.Test;
import wxdgaming.boot.agent.system.AnnUtil;
import wxdgaming.boot.assist.JavaAssistBox;
import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.TextMappingProxy;
import wxdgaming.boot.net.controller.ann.TextMapping;
import wxdgaming.mmo.script.loginsr.server.RpcController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-22 08:47
 **/
public class ImplTest {

    @Test
    public void t1() throws Exception {
        JavaAssistBox javaAssistBox = JavaAssistBox.of();
        Class<RpcController> rpcControllerClass = RpcController.class;
        Method[] methods = rpcControllerClass.getDeclaredMethods();
        for (Method method : methods) {
            if (AnnUtil.ann(method, TextMapping.class) == null) continue;
            String strCode = """
                    public void proxy(Object out, Object instance, Object[] params) {
                        AtomicReference atomicReference = (AtomicReference) out;
                        Object ret = null;
                        %s((%s) instance).%s(
                               (%s) params[0],
                               (%s) params[1]
                        );
                        atomicReference.set(ret);
                    }
                    """.formatted(
                    "",
                    rpcControllerClass.getName(),
                    method.getName(),
                    SocketSession.class.getName(),
                    ObjMap.class.getName()
            );

            JavaAssistBox.JavaAssist javaAssist = javaAssistBox.extendSuperclass(
                    TextMappingProxy.class,
                    TextMappingProxy.class.getClassLoader()
            );

            javaAssist.importPackage(TextMappingProxy.class.getPackageName());
            javaAssist.importPackage(AtomicReference.class.getPackageName());
            javaAssist.importPackage(rpcControllerClass.getPackageName());
            javaAssist.importPackage(SocketSession.class.getPackageName());
            javaAssist.importPackage(ObjMap.class.getPackageName());

            System.out.println(strCode);
            javaAssist.createMethod(strCode);

            javaAssist.writeFile("target/out");
            //Class aClass = javaAssist.loadClass();
            //TextMappingProxy textMappingProxy = javaAssist.toInstance();
            //textMappingProxy.proxy(new AtomicReference<>(), new RpcController(), new Object[]{null, null});
        }
    }

    @Test
    public void t2() {
        JavaAssistBox javaAssistBox = JavaAssistBox.of();
        Class<RpcController> rpcControllerClass = RpcController.class;
        Method[] methods = rpcControllerClass.getDeclaredMethods();
        for (Method method : methods) {
            if (AnnUtil.ann(method, TextMapping.class) == null) continue;
            JavaAssistBox.JavaAssist javaAssist = javaAssistBox.extendSuperclass(
                    TextMappingProxy.class,
                    RpcController.class.getClassLoader()
            );
            javaAssist.importPackage(AtomicReference.class);
            javaAssist.importPackage(TextMappingProxy.class);
            javaAssist.importPackage(RpcController.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("public void proxy(Object out, Object instance, Object[] params) {").append("\n");
            stringBuilder.append("    AtomicReference atomicReference = (AtomicReference) out;").append("\n");
            if (void.class.equals(method.getReturnType()) || Void.class.equals(method.getReturnType())) {
                stringBuilder.append("    Object ret = null;").append("\n");
            } else {
                javaAssist.importPackage(method.getReturnType().getPackageName());
                stringBuilder.append("    ").append(method.getReturnType().getSimpleName()).append(" ret = null;").append("\n");
                stringBuilder.append("    ret = ");
            }
            stringBuilder.append(String.format("((%s) instance).%s(", RpcController.class.getSimpleName(), method.getName()));
            if (method.getParameters() != null) {
                for (int i = 0; i < method.getParameters().length; i++) {
                    if (i > 0) stringBuilder.append(",");
                    stringBuilder.append("\n");
                    Parameter parameter = method.getParameters()[i];
                    stringBuilder.append(String.format("        (%s) params[%s]", parameter.getType().getSimpleName(), i));
                    javaAssist.importPackage(parameter.getType());
                }
            }
            stringBuilder.append(");").append("\n");
            stringBuilder.append("    atomicReference.set(ret);").append("\n");
            stringBuilder.append("}").append("\n");
            String strCode = stringBuilder.toString();
            System.out.println(strCode);
            javaAssist.createMethod(strCode);
            javaAssist.writeFile("target/out");
            TextMappingProxy textMappingProxy = javaAssist.toInstance();
            javaAssist.getCtClass().defrost();
            javaAssist.getCtClass().detach();
        }
    }

}
