package com.keven.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 存储发布的服务
 */
@Component
public class InitialMediator implements BeanPostProcessor{

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //加了服务发布标记的bean（@KevenRemoteService）进行远程发布
        if(bean.getClass().isAnnotationPresent(KevenRemoteService.class)){
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method method : methods){
                // 接口全路径和方法组装的key （内部路由 key -> 方法）
                String key = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key,beanMethod);
            }
        }
        return bean;
    }
}
