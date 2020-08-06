package com.keven.rpc;

@KevenRemoteService
public class TestServiceImpl implements ITestService{
    @Override
    public String sayHello() {
        return "RETURN SAYHELLO";
    }
}
