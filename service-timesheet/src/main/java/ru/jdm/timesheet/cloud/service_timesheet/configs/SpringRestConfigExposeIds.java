package ru.jdm.timesheet.cloud.service_timesheet.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;


/**
 *=Configuration Class for expose Entity ids
 * 20201.12.01
 */
@Configuration
public class SpringRestConfigExposeIds implements RepositoryRestConfigurer {

    //--Inject/Autowire JPA entity manager
    private EntityManager entityManager;
    //- constructor injection
    @Autowired
    public SpringRestConfigExposeIds(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        //--Call internal helper-method for expose Entity ids
        exposeId(config);
    }

    //--Implement internal helper-method for expose Entity ids
    private void exposeId(RepositoryRestConfiguration config) {
        //- gets a list/collection of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        //- create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();
        //- get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }
        //- expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
