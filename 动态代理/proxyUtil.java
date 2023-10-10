package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxyUtil {
    public static Star createProxy(bigStar para){
        Star star=(Star) Proxy.newProxyInstance(
                proxyUtil.class.getClassLoader(),
                new Class[]{Star.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if("sing".equals(method.getName())) System.out.print("准备话筒，收钱\n");
                        else if("dance".equals(method.getName())) System.out.print("准备场地，收钱\n");

                        return method.invoke(para,args);
                    }
                }

        );
        return star;
    }
}
