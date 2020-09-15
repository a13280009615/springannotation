package com.zf.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;


@Aspect
public class LogAspects {


    @Pointcut("execution(public int  com.zf.aop.MyCalculator.*(int,int))")
    public void pointCut() {

    }

    /**
     *
     * @param joinPoint  必须出现在第一位
     */
    @Before("pointCut()")
    public void LogBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "运行之前 参数是" + Arrays.asList(args));
    }

    @After("pointCut()")
    public void LogAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "运行之后 参数是" + Arrays.asList(args));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void LogAfterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "运行完成 返回值是" + result);
        System.out.println("测试提交git");
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void LogAfterThrowing(JoinPoint joinPoint, Exception e) {

        String name = joinPoint.getSignature().getName();
        System.out.println(name + "运行 出现异常" + e);
    }
}
