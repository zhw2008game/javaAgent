package com.zhw.javaagent;


import org.apache.http.client.methods.HttpGet;

public class AgentService {
    // agent地址:D:\javaWorkspace\gitHub\javaAgent\agent_client\target\agent_client-1.0.1.jar
    public static void main(String[] args) {
        System.out.println("AgentService.main()方法被执行");
        HttpGet httpGet = new HttpGet();
        System.out.println(httpGet.getMethod());
    }
}
