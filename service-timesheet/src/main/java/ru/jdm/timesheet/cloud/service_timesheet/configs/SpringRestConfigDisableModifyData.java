package ru.jdm.timesheet.cloud.service_timesheet.configs;

import ru.jdm.timesheet.cloud.service_timesheet.entity.Orgdata;
import ru.jdm.timesheet.cloud.service_timesheet.entity.User;
import ru.jdm.timesheet.cloud.service_timesheet.entity.Timedata;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


/**
 *=Configuration Class for DISABLE some HTTP-Methods: POST, PUT, DELETE (enable read-only Service mode)
 */
@Configuration
public class SpringRestConfigDisableModifyData implements RepositoryRestConfigurer {

    //=ATTENTION:
    // - configureRepositoryRestConfiguration() Method IS NOT EXISTS in new version of Spring Boot (2.5.6)
    //   and this produce an error: "Method does not override method from its superclass";
    // - in previous Spring Boot version (2.4.12) this Method is Exists, but in deprecation status!
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        //--Use Method enumerators when create list of enums (возможно я не правильно понял.. это-же енумераторы?)
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //--disable HTTP methods (PUT,POST,DELETE) for SERVICE-ORGDATA:
        //  *lambda-expression syntax
        config.getExposureConfiguration()
                // select Entity Class
                .forDomainType(Orgdata.class)
                // disable single element/items modification
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                // disable multiply elements/items (collection) modification
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        //--disable HTTP methods (PUT,POST,DELETE) for SERVICE-USER:
        //  *lambda-expression syntax
        config.getExposureConfiguration()
                // select Entity Class
                .forDomainType(User.class)
                // disable single element/items modification
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                // disable multiply elements/items (collection) modification
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        //--disable HTTP methods (PUT,POST,DELETE) for SERVICE-TIMEDATA:
        //  *lambda-expression syntax
        config.getExposureConfiguration()
                // select Entity Class
                .forDomainType(Timedata.class)
                // disable single element/items modification
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                // disable multiply elements/items (collection) modification
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

    }
}
