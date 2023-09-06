package com.kunning.web.mybatis.integrate;

import com.kunning.web.mybatis.dao.UserDao;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class LubanFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {

        UserDao userDao = (UserDao) Proxy.newProxyInstance(LubanFactoryBean.class.getClassLoader(), new Class[]{UserDao.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (Object.class.equals(method.getDeclaringClass())) {
                    System.out.println("动态代理");
                    return method.invoke(this, args);
                } else {
                    System.out.println(method.getAnnotation(Select.class).value()[0]);
                    return null;
                }
            }
        });

//        return new C();
        return userDao;
    }

    @Override
    public Class<?> getObjectType() {
        return C.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public String toString() {
        return "LubanFactoryBean{冯仕清}";
    }
}
