package com.keven.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 手动版
 */
public class RpcProxyServer {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service, int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            while(true){
                //监听客户端请求
                Socket socket = serverSocket.accept();
                // socket是阻塞通信（连接阻塞、io阻塞），线程 ProcessorHandler 为了减少io阻塞，一个线程处理多个连接（线程资源受限问题）
                // executorService.execute(new ProcessorHandler(socket,service)); 非注解
                executorService.execute(new ProcessorHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
