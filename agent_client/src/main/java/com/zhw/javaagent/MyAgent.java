package com.zhw.javaagent;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    /**
     * Explain: 优先执行此方法，如果不包含2个参数的方法，则执行下面一个参数的
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("======MyAgent的premain(String,Instrumentation)被执行=======");
    }

    /**
     * Explian: 次级执行方法，如果类里不包含上面2个参数方法，则执行此方法
     * @param agentArgs
     */
    public static void premain(String agentArgs){
        System.out.println("======MyAgent的premain(String)被执行=======");
    }
}
