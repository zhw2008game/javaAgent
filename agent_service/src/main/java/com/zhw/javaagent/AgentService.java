package com.zhw.javaagent;


import org.apache.http.client.methods.HttpGet;

public class AgentService {
    public static void main(String[] args) {
        System.out.println("AgentService.main()方法被执行");
        HttpGet httpGet = new HttpGet();
        httpGet.getMethod();
    }
}
