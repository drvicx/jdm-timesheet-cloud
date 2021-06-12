package ru.jdm.timesheet.cloud.service_user.configs;

//--Spring Framework
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
//--Swagger/OpenAPI Tools
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
//--Java Core
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;


/**
 *=User Persistence Configuration
 */
@EnableSwagger2
@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "ru.jdm.timesheet.cloud.service_user.dao.user",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
public class PersistenceUserConfiguration {
    @Autowired
    private Environment env;

    //--Swagger Bean Configuration;
    @Primary
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    //--Entity Manager Bean Configuration;
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan("ru.jdm.timesheet.cloud.service_user.entity.user");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    //--DataSource Bean Configuration;
    @Primary
    @Bean
    public DataSource userDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("app.hsql.jdbc.driverClassName")));
        dataSource.setUrl(env.getProperty("app.hsql.jdbc.url"));
        dataSource.setUsername(env.getProperty("app.hsql.jdbc.user"));
        dataSource.setPassword(env.getProperty("app.hsql.jdbc.pass"));

        return dataSource;
    }

    //--Spring API Transaction Manager Bean Configuration;
    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManager().getObject());
        return transactionManager;
    }

}
