package br.com.samueltorga.orderprocessing.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class ReadOnlyRoutingAspect {

    @Around("@annotation(tx)")
    public Object routeDataSource(ProceedingJoinPoint joinPoint, Transactional tx) throws Throwable {
        try {
            DataSourceType type = tx.readOnly() ? DataSourceType.REPLICA : DataSourceType.MASTER;
            log.trace("Method {} is using DataSource: {}", joinPoint.getSignature().toShortString(), type);
            DataSourceContextHolder.set(type);
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }

}
