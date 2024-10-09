//package com.dousnl.controller;
//
//import com.google.common.collect.Sets;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtMethod;
//import javassist.bytecode.MethodInfo;
//import javassist.bytecode.Mnemonic;
//import org.apache.dubbo.config.annotation.Reference;
//import org.reflections.ReflectionUtils;
//import org.springframework.aop.framework.AopProxyUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.*;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//
///**
// * Description:
// * Company    : 上海黄豆网络科技有限公司
// *
// * @author : hll
// * Date       : 2024/7/16
// * Modify     : 修改日期          修改人员        修改说明          JIRA编号
// * v1.0.0       2024/7/16       hll    新增              1001
// ********************************************************************/
//@Component
//public class SpringContainerReferenceScanner {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    private static final Set<String> methodSet = Sets.newLinkedHashSet();
//
//    public void scanForControllers() {
//        Map<String, Object> restControllers = applicationContext.getBeansWithAnnotation(RestController.class);
//        for (Object controller : restControllers.values()) {
//            final Object singletonTarget = AopProxyUtils.getSingletonTarget(controller);
//            checkForSpringContainerReferences(singletonTarget);
//        }
//        Map<String, Object> restComponents =  applicationContext.getBeansWithAnnotation(Component.class);
//        for (Object controller : restComponents.values()) {
//            final Object singletonTarget = AopProxyUtils.getSingletonTarget(controller);
//            checkForSpringContainerReferences(singletonTarget);
//        }
//
//        try {
//            // 创建一个文件输出流
//            // 创建一个对象输出流
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\set.txt"), "UTF-8"));
//            // 将Set对象写入文件
//            for (String s : methodSet) {
//                String methodStr = s+"\n";
//                writer.write(methodStr);
//            }
//
//            // 关闭流
//            writer.close();
//            writer.close();
//            System.out.println("Set对象已成功保存到文件set.txt中。");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            final HashSet<String> strings = Sets.newHashSet("123123131awdada", "sfsdfsfsdffd", "sdgdfgdfetert3535");
//            // 创建一个文件输出流
//            // 创建一个对象输出流
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\set.txt"), "UTF-8"));
//            // 将Set对象写入文件
//            for (String s : strings) {
//                String methodStr = s+"\n";
//                writer.write(methodStr);
//            }
//
//            // 关闭流
//            writer.close();
//            writer.close();
//            System.out.println("Set对象已成功保存到文件set.txt中。");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void checkForSpringContainerReferences(Object controller) {
//        if (controller == null || controller.getClass() == null || controller.getClass().getDeclaredFields() == null){
//            return;
//        }
//        Field[] fields = controller.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            final Set<Annotation> allAnnotations = ReflectionUtils.getAllAnnotations(field, (t) -> {
//                return t.equals(Autowired.class) || t.equals(Reference.class);
//            });
//            if(field.isAnnotationPresent(Autowired.class)||field.isAnnotationPresent(Reference.class)) {
//                Class<?> type = field.getType();
//                Object springBean = applicationContext.getBean(type);
//                if (springBean != null) {
//                    // 这里可以处理找到的Spring容器中的类，比如打印信息等
//                    System.out.println("Controller " + controller.getClass().getName() + "======>" + type.getName());
//
//                    final Method[] methods = type.getMethods();
//                    for (Method method : methods) {
//                        if (method.getName().equals("toString") ||
//                                method.getName().equals("hashCode") ||
//                                method.getName().equals("equals") || method.getName().equals("wait")
//                                || method.getName().equals("getClass")){
//                            continue;
//                        }
//                        System.out.println(type.getName() + "." +method.getName());
//                        methodSet.add(type.getName() + "." +method.getName());
//                    }
//
//                    System.out.println("--------------------------");
//                }
//            }
//
////            Method[] methods = controller.getClass().getMethods();
////            for (Method method : methods) {
////                try {
////
////                    final String methodBytecode = getMethodBytecode(controller.getClass(), method.getName());
////                    System.out.println(methodBytecode);
////                    if (methodBytecode.contains(field.getName())) {
////                        final String str = new String(methodBytecode);
////                        System.out.println(field.getName() +".");
////                    }
////                } catch (Exception e) {
////                    continue;
////                }
////            }
//        }
//    }
//
//    public static String getMethodBytecode(Class<?> clazz, String methodName) throws Exception {
//        // 获取类池
//        ClassPool pool = ClassPool.getDefault();
//        // 获取 CtClass 对象
//        CtClass ctClass = pool.get(clazz.getName());
//
//        //查找方法
//        CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
//        MethodInfo methodInfo = ctMethod.getMethodInfo();
//        //获取字节码
//        final byte[] code = methodInfo.getCodeAttribute().getCode();
//
//        // 将字节码转换为字符串
//        StringBuilder bytecodeString = new StringBuilder();
//        for (int i = 0; i < code.length; i++) {
//            byte opcode = code[i];
//            String mnemonic = Mnemonic.OPCODE[opcode & 0xFF];
//            bytecodeString.append(mnemonic).append(" ");
//        }
//
//        return bytecodeString.toString();
//    }
//
//
//
//}
