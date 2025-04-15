package br.com.samueltorga.orderprocessing.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get() != null ? DataSourceContextHolder.get() : DataSourceType.MASTER;
    }
}
