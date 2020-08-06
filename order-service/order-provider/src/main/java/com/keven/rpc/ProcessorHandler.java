package com.keven.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable{

    // private Object service; // 非注解
    private Socket socket;

    /* 非注解
    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }*/

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());//客户端传过来的request
            RpcRequest request = (RpcRequest)inputStream.readObject(); //反序列化
            // Object rs = invoke(request); // 非注解
            // 路由
            Mediator mediator = Mediator.getInstance();
            Object rs = mediator.processor(request);
            System.out.println("服务端的执行结果："+ rs);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* 非注解
    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //通过反射进行服务的调用
        Class clazz = Class.forName(request.getClassName());
        //找到目标方法
        Method method = clazz.getMethod(request.getMethodName(), request.getTypes());
        return method.invoke(service, request.getArgs());
    }*/
}
