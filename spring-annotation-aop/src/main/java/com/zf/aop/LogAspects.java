package com.zf.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author zhengfan
 * @create 2020-09-27 0:47
 */
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     * 1 本类引用
     */
    @Pointcut("execution(public int com.zf.aop.MathCalculator.*(..))")
    public  void pointCut(){

    }

    //目标方法之前切入  切入点表达式 指定在哪个方法切入
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"运行..... 参数列表是:{"+ Arrays.asList(args) +"}");

    }
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"结束.... ");
    }
    @AfterReturning(value = "pointCut()",returning ="result" )
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"正常返回..... 结果是:{"+result+"}");
    }

    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void logException(JoinPoint joinPoint,Exception e){
        System.out.println(joinPoint.getSignature().getName()+"异常..... 异常信息是:{"+e+"}");
    }
}
