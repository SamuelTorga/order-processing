package br.com.samueltorga.orderprocessing.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.trace("Using DataSourceType: {}", DataSourceContextHolder.get());
        return DataSourceContextHolder.get() != null ? DataSourceContextHolder.get() : DataSourceType.MASTER;
    }
}
