package com.zhw.javaagent.client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyAgent {

    public String getName(){
        return "my name is myAgent!";
    }

    /**
     * Explain: 优先执行此方法，如果不包含2个参数的方法，则执行下面一个参数的
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("======MyAgent的premain(String,Instrumentation)被执行=======");
        inst.addTransformer(new MyClassTransformer());
    }

    /**
     * Explian: 次级执行方法，如果类里不包含上面2个参数方法，则执行此方法
     * @param agentArgs
     */
    public static void premain(String agentArgs){
        System.out.println("======MyAgent的premain(String)被执行=======");
    }

    static class MyClassTransformer implements ClassFileTransformer {
        /**
         *
         * @param loader                the defining loader of the class to be transformed,
         *                              may be <code>null</code> if the bootstrap loader
         * @param className             the name of the class in the internal form of fully
         *                              qualified class and interface names as defined in
         *                              <i>The Java Virtual Machine Specification</i>.
         *                              For example, <code>"java/util/List"</code>.
         * @param classBeingRedefined   if this is triggered by a redefine or retransform,
         *                              the class being redefined or retransformed;
         *                              if this is a class load, <code>null</code>
         * @param protectionDomain      the protection domain of the class being defined or redefined
         * @param classfileBuffer       the input byte buffer in class file format - must not be modified
         *
         * @return
         * @throws IllegalClassFormatException
         */
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            if (className != null && className.indexOf("HttpGet") > 0) {
                try {
                    /**
                     * 这里需要注意的是方法参数、返回值、方法数量都应和原始代码保持一直，这样才能避免调用时出现异常，
                     * 在这一点上，可以假想我们所写的类与原始类实现了相同的接口，除此之外，全类名（包括包名）需和原始代码保持一致，
                     * 不然替换时就会抛出异常。
                     */
                    String fileUrl = "D:\\javaWorkspace\\zhw\\javaAgent\\agent_client\\src\\main\\java\\org\\apache\\http\\client\\methods\\HttpGet.class";
                    InputStream fileInputStream = new FileInputStream(fileUrl);
                    byte[] bytes = new byte[fileInputStream.available()];
                    fileInputStream.read(bytes);
                    return bytes;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return classfileBuffer;
        }
    }
}
