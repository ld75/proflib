package com.proflib.infrastructure.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class DataSource {
    Logger logger =  LoggerFactory.getLogger(DataSource.class);
    @PersistenceContext
    EntityManager em;

        /*@Resource(name="comptesds")
        DataSource myDB;*/
    @Produces
    public EntityManager getEm() {
        return em;
    }
    @PostConstruct
    public void debut() {
        logger.debug("INITIALISE "+em);
    }

}

