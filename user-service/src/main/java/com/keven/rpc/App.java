package com.keven.rpc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        ITestService testService = rpcProxyClient.clientProxy(ITestService.class,"localhost",8080);

        System.out.println(testService.sayHello());
    }
}
