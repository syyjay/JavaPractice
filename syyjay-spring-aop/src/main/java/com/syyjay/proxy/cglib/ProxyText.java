package com.syyjay.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyText {
    public static void main(final String[] args) {
        //目标对象
        final Target target = new Target();

        //增强对象
        final Advice advice = new Advice();

        //返回值即 动态生成代理对象 基于cflib

        //创建增强器
        Enhancer enhancer = new Enhancer();

        //设置父类
        enhancer.setSuperclass(Target.class);

        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                advice.before();
                Object invoke = method.invoke(target, args);//执行目标
                advice.afterReturning();
                return invoke;
            }
        });
        Target prxoy = (Target) enhancer.create();
        prxoy.save();
    }
}
