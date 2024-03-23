package code.impl;

import org.junit.Test;
import wxdgaming.boot.agent.exception.Throw;
import wxdgaming.boot.agent.system.AnnUtil;
import wxdgaming.boot.assist.JavaAssistBox;
import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.str.TemplatePack;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.TextMappingProxy;
import wxdgaming.boot.net.controller.ann.TextMapping;
import wxdgaming.mmo.script.loginsr.server.RpcController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            javaAssist.importPackage(Throw.class);
            javaAssist.importPackage(Throwable.class);
            javaAssist.importPackage(AtomicReference.class);
            javaAssist.importPackage(TextMappingProxy.class);
            javaAssist.importPackage(RpcController.class);
            HashMap<String, Object> datas = new HashMap<>();
            if (void.class.equals(method.getReturnType()) || Void.class.equals(method.getReturnType())) {
                datas.put("ret_type", "Object");
                datas.put("ret_set", "");
            } else {
                javaAssist.importPackage(method.getReturnType().getPackageName());
                datas.put("ret_type", method.getReturnType().getSimpleName());
                datas.put("ret_set", "ret = ");
            }
            datas.put("instanceClass", RpcController.class.getSimpleName());
            datas.put("methodName", method.getName());
            datas.put("paramTypes", "");
            if (method.getParameters() != null) {
                List<String> paramTypes = new ArrayList<>();
                for (int i = 0; i < method.getParameters().length; i++) {
                    Parameter parameter = method.getParameters()[i];
                    javaAssist.importPackage(parameter.getType());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(String.format("(%s) params[%s]", parameter.getType().getSimpleName(), i));
                    if (i < method.getParameters().length - 1) {
                        stringBuilder.append(",");
                    }
                    paramTypes.add(stringBuilder.toString());
                }
                datas.put("paramTypes", paramTypes);
            }

            String codeString = TemplatePack.ftl2String(
                    rpcControllerClass.getClassLoader(),
                    "template/mapping",
                    "proxy.ftl",
                    datas
            );

            System.out.println(codeString);
            javaAssist.createMethod(codeString);
            javaAssist.writeFile("target/out");
            TextMappingProxy textMappingProxy = javaAssist.toInstance();
            javaAssist.getCtClass().defrost();
            javaAssist.getCtClass().detach();
        }
    }

}
