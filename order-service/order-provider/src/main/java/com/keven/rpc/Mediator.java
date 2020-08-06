package com.keven.rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 中间类（实例化之后、初始化之前 BeanPostProcessor）
 */
public class Mediator {

    //用来存储发布的服务的实例(服务调用的路由)
    public static Map<String ,BeanMethod> map = new ConcurrentHashMap<>();

    private volatile static Mediator instance;

    private Mediator(){}

    // 路由单例
    public static Mediator getInstance(){
        // 双检索
        if(instance == null){
            synchronized (Mediator.class){
                if(instance == null){
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest request){
        // key -> bean
        String key = request.getClassName() + "." + request.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if(beanMethod == null){
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            return method.invoke(bean, request.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
