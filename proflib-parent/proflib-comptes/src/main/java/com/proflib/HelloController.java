package com.proflib;

import com.proflib.domaine.comptes.sousdomaine.Eleve;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @Inject
    EntityManager ds;

    @GET
    public String sayHello() {
        List<Eleve> eleves = ds.createNamedQuery("Eleve.findAll").getResultList();
        return "Hello World" + ds.toString() +":" +eleves.size();
    }
}
