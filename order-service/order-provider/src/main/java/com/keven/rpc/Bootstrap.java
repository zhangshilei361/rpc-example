package com.keven.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.keven.rpc")
public class Bootstrap {

    public static void main(String[] args) {
        /* 非注解调用方式
        IOrderService orderService = new OrderServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(orderService, 8080);
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Bootstrap.class);
    }
}
