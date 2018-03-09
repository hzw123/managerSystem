package com.managerSystem.oasys.Aspect;

import com.managerSystem.oasys.utils.UtilsClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order(4)
@Component
public class WebLogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long> startTime=new ThreadLocal<Long>();

    @Pointcut("execution(public * com.managerSystem.oasys.controller.*.*(..))")
    public void webLog(){

    }

    @Around("webLog()")
    public Object advice(ProceedingJoinPoint joinPoint)throws Throwable{

        long beginTime=System.currentTimeMillis();
        try{
            HttpServletRequest request= UtilsClass.getRequest();
            String method=request.getMethod();
            String uri=request.getRequestURI();
            String methodName=joinPoint.getSignature().getName();

            logger.info("URL:"+request.getRequestURL().toString());
            logger.info("HTTP_REQUEST:"+method);
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName());
            logger.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));
            logger.info("ip:"+ request.getRemoteAddr());

            logger.debug("[uri="+uri+",methods="+method+",methodName="+methodName);
            logger.debug("SPEND TIME(millisecond)"+(System.currentTimeMillis()-beginTime)+"s");

            return joinPoint.proceed();
        }catch (Throwable e){
            logger.debug("Exception",e);
            logger.debug("SPEND TIME(millisecond)"+(System.currentTimeMillis()-beginTime)+"s");
            throw e;
        }

    }

}
