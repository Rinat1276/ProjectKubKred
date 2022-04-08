package ru.kubkred.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "ru.kubkred.dao.repository")
@Configuration
public class Config {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlite:db/dbo.db");
        dataSource.setDriverClassName("org.sqlite.JDBC");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactory = new LocalContainerEntityManagerFactoryBean();
        managerFactory.setDataSource(dataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        managerFactory.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
        managerFactory.setJpaProperties(properties);
        managerFactory.setPackagesToScan("ru.kubkred.dao.entity");

        return managerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory managerFactory) {
        return new JpaTransactionManager(managerFactory);
    }
}
