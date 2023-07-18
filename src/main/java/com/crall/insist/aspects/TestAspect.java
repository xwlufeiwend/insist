package com.crall.insist.aspects;

import com.crall.insist.aspects.annotation.MyLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class TestAspect {
    //切面
    //@Pointcut("execution(* com.crall.insist.controller.*(..))")
    @Pointcut("@annotation(com.crall.insist.aspects.annotation.MyLog)")
    private void testAdvice(){}

    /**
     * 前置处理
     */
    @Before("testAdvice()")
    public void before(){
        System.out.println("前置通知！");
    }

    /**
     * 后置处理
     */
    @After("testAdvice()")
    public void after(){
        //方法执行完毕
        System.out.println("后置处理！");
    }

    /**
     * 后置返回通知
     */
    @AfterReturning("testAdvice()")
    public void afterReturning(){
        System.out.println("后置返回通知！");
    }

    /**
     * 异常处理
     */
    /*@AfterThrowing
    public void afterThrow(){
        System.out.println("后置异常处理！");
    }
*/
    /**
     * 环绕
     */
    @Around("testAdvice()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前！");
        //得到连接点执行的方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("method" + method.toString());
        String[] parameterNames = signature.getParameterNames();
        System.out.println("parameterNames" + Arrays.toString(parameterNames));
        Object[] args = joinPoint.getArgs();
        System.out.println("args" + Arrays.toString(args));
        //得到方法上的注解
        MyLog annotation = method.getAnnotation(MyLog.class);
        if (annotation != null){
            String value = annotation.value();
            if (value.equals("weather"))
                System.out.println(value);
        }
        return joinPoint.proceed();
    }

}
