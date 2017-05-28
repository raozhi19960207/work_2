package com.smallsoho.test.aspectj;
import android.provider.Settings;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by raozhi on 2017/5/26.
 */

@Aspect
public class TraceAspect
{
    private String TAG = "TAG ";
    private static final String POINT_METHOD = "execution(* com.example.administrator.searchpicturetool..*.*(..))";
    private static final String POINT_CALLMETHOD = "call(* com.example.administrator.searchpicturetool..*.*(..))";
    @Pointcut(POINT_METHOD)
    public void methodAnnotated()
    {
    }
    @Pointcut(POINT_CALLMETHOD)
    public void methodCallAnnotated(){}
    @Around("methodAnnotated()")
//    public Object aronudWeaverPoint(ProceedingJoinPoint joinPoint) throws Throwable {
//        joinPoint.proceed();
//        String result = "----------------------------->aroundWeaverPoint";
//        Log.e(TAG,"----------------------------->aroundWeaverPoint");
//        return  result;//替换原方法的返回值
//    }
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try
        {
            Object result = joinPoint.proceed();
            long end= System.currentTimeMillis();
            Log.e(TAG,df.format(new Date())+"\taround:\tUse function:" + joinPoint.toShortString() + "\tUse time : " + (end - start) + " ms!");
            return result;
        }
        catch (Throwable e)
        {
            long end = System.currentTimeMillis();
            Log.e(TAG,df.format(new Date())+"\taround:\tUse function:" + joinPoint.toShortString() + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            throw e;
        }
    }
//    @Before("methodCallAnnotated()")
//    public void beforecall(JoinPoint joinPoint){
//        Log.e(TAG,joinPoint.toShortString());
//    }
//


}
