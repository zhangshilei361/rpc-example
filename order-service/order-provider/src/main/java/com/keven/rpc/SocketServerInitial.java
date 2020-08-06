package com.keven.rpc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 注解版
 */
//spring 容器启动完成之后，会发布一个ContextRefreshedEvent事件
@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent>{

    private final ExecutorService executorService= Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动服务
        ServerSocket serverSocket=null;
        try {
            // 暂时写死，可以写成配置
            serverSocket = new ServerSocket(8888);
            while(true){
                Socket socket = serverSocket.accept(); //监听客户端请求
                // executorService.execute(new ProcessorHandler(socket, service)); service InitialMediator 已处理过
                executorService.execute(new ProcessorHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
