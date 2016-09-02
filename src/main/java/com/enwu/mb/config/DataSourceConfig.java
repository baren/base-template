package com.enwu.mb.config;

/**
 * Created by user on 16/7/5.
 */
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:spring/data-access.properties")
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    @Profile("tomcat-jdbc")
    @Description("DataSource configuration for the tomcat jdbc connection pool")
    public DataSource dataSource() {
        // See here for more details on commons-dbcp versus tomcat-jdbc:
        // http://blog.ippon.fr/2013/03/13/improving-the-performance-of-the-spring-petclinic-sample-application-part-3-of-5/-->
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }


    @Bean(name="dataSource")
    @Profile("c3p0-jdbc")
    @Description("c3p0 jdbc connection pool")
    public DataSource pooledDataSource() {
        com.mchange.v2.c3p0.ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }


        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));


//        dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("hibernate.c3p0.max_size")));
//        dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("hibernate.c3p0.min_size")));
//        dataSource.setCheckoutTimeout(Integer.parseInt(properties.getProperty("hibernate.c3p0.timeout")));
//        dataSource.setMaxStatements(Integer.parseInt(properties.getProperty("hibernate.c3p0.max_statements")));
//        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("hibernate.c3p0.idle_test_period")));
//        dataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty("hibernate.c3p0.acquire_increment")));

        return dataSource;
    }
}