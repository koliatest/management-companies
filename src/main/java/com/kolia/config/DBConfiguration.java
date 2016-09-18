package com.kolia.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://node34110-management-companies.unicloud.pl/user";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "12345678";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "user";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.kolia.model";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "true";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "update";


    @Bean
    public SessionFactory sessionFactory() {

        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN).addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.format_sql", PROPERTY_NAME_HIBERNATE_FORMAT_SQL);
        properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
        properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
        properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);

        return properties;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {

        return new HibernateTransactionManager(sessionFactory());
    }
}
