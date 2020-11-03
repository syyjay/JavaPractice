package com.syyjay.anno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect
public class MyAspect {
//    @Before("execution(public void com.syyjay.anno.Target.save())")
    @Before("MyAspect.myPoint()")
    public void before(){
        System.out.println("前置代码增强....");
    }

    @Pointcut("execution(public void com.syyjay.anno.Target.save())")
    /*切点表达式抽取*/
    public void myPoint(){}
}
