package com.keven.rpc;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    /**
     * 远程调用动态代理
     *
     * @param interfaceCls java默认代理接口
     * @param host 远程服务地址 可略 后在 RemoteInvocationHandler invoke（）设定
     * @param port 远程服务端口 可略 后在 RemoteInvocationHandler invoke（）设定
     * @param <T> 泛型
     * @return
     */
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port){

        // 参数 接口、类、InvocationHandler（调用invoke方法处理事情）
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler());
    }
}
