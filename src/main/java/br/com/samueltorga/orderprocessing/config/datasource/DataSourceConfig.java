package br.com.samueltorga.orderprocessing.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "br.com.samueltorga.orderprocessing.model")
@EnableJpaRepositories(basePackages = "br.com.samueltorga.orderprocessing.repository")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariProperties() {
        return new HikariConfig();
    }

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(DataSourceProperties masterDataSourceProperties,
                                       HikariConfig hikariProperties) {
        HikariConfig config = new HikariConfig(hikariProperties.getDataSourceProperties());
        config.setJdbcUrl(masterDataSourceProperties.getUrl());
        config.setUsername(masterDataSourceProperties.getUsername());
        config.setPassword(masterDataSourceProperties.getPassword());
        config.setPoolName("HikariMaster");
        return new HikariDataSource(config);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.replica")
    public DataSourceProperties replicaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "replicaDataSource")
    public DataSource replicaDataSource(@Qualifier("replicaDataSourceProperties") DataSourceProperties replicaDataSourceProperties,
                                        HikariConfig hikariProperties) {
        HikariConfig config = new HikariConfig(hikariProperties.getDataSourceProperties());
        config.setJdbcUrl(replicaDataSourceProperties.getUrl());
        config.setUsername(replicaDataSourceProperties.getUsername());
        config.setPassword(replicaDataSourceProperties.getPassword());
        config.setPoolName("HikariReplica");
        config.setReadOnly(true);
        return new HikariDataSource(config);
    }

    @Primary
    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource master,
            @Qualifier("replicaDataSource") DataSource replica
    ) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.MASTER, master);
        dataSourceMap.put(DataSourceType.REPLICA, replica);

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(master);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.afterPropertiesSet();
        return routingDataSource;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("routingDataSource") DataSource routingDataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(routingDataSource)
                .packages("br.com.samueltorga.orderprocessing.model")
                .persistenceUnit("default")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }

}
