package com.keven.rpc;

import com.keven.rpc.RpcRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
public class RemoteInvocationHandler implements InvocationHandler{
    @Value("${keven.host}")
    private String host;
    @Value("${keven.host}")
    private int port;

    public RemoteInvocationHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 建立远程连接
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host,port);
        // 传递数据 - 调用哪个接口、 哪个方法、方法的参数(服务端反射调用)
        RpcRequest request = new RpcRequest();
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        //参数类型
        request.setTypes(method.getParameterTypes());
        request.setMethodName(method.getName());
        return rpcNetTransport.send(request);
    }
}
