package ru.jdm.timesheet.cloud.service_timedata.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;


/**
 *=Timedata Persistence Configuration
 */
@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "ru.jdm.timesheet.cloud.service_timedata.dao.timedata",
        entityManagerFactoryRef = "timedataEntityManager",
        transactionManagerRef = "timedataTransactionManager"
)
public class PersistenceTimedataConfiguration {
    @Autowired
    private Environment env;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean timedataEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(timedataDataSource());
        em.setPackagesToScan("ru.jdm.timesheet.cloud.service_timedata.entity.timedata");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource timedataDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("app.hsql.jdbc.driverClassName")));
        dataSource.setUrl(env.getProperty("app.hsql.jdbc.url"));
        dataSource.setUsername(env.getProperty("app.hsql.jdbc.user"));
        dataSource.setPassword(env.getProperty("app.hsql.jdbc.pass"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager timedataTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(timedataEntityManager().getObject());
        return transactionManager;
    }

}
