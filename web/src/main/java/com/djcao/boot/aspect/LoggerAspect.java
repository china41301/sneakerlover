package com.djcao.boot.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author djcao
 * @workcode BG389966
 * @date 2019/7/18
 */
@Aspect
@Component
public class LoggerAspect {

    Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(public * com.djcao.boot.controller.*.*( .. ))")
    public void pointcut(){
    }

    @Around(value = "pointcut()")
    public Object aroutd(ProceedingJoinPoint pjp) throws Throwable {
        try {
            try{
                logger.info(pjp.getTarget().getClass().getSimpleName()+"."+pjp.getSignature().getName
                    ()+" args:"+JSON.toJSONString(pjp.getArgs()));
            }catch(Throwable t){

            }
            Object proceed = pjp.proceed();
            logger.info(JSON.toJSONString(proceed));
            return proceed;
        } catch (Throwable throwable) {
            logger.error("loggerAspect",throwable);
            throw throwable;
        }
    }
}
