package br.com.samueltorga.orderprocessing.config.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class ReadOnlyRoutingAspect {

    @Around("@annotation(tx)")
    public Object routeDataSource(ProceedingJoinPoint joinPoint, Transactional tx) throws Throwable {
        try {
            if (tx.readOnly()) {
                DataSourceContextHolder.set(DataSourceType.REPLICA);
            } else {
                DataSourceContextHolder.set(DataSourceType.MASTER);
            }
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }

}
