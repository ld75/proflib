package com.proflib.repository;


import com.proflib.domaine.comptes.sousdomaine.Eleve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EleveRepository implements IEleveRepository{
    Logger logger =  LoggerFactory.getLogger(EleveRepository.class);
    @PersistenceContext
    EntityManager em;

    @Override
    public void createEleve(Eleve eleve) {
        logger.debug(eleve.toString());
        em.persist(eleve);
        em.flush();
        logger.debug("eleve cree!!");
    }
}

