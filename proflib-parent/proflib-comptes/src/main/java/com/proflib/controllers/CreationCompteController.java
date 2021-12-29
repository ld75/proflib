package com.proflib.controllers;

import com.proflib.domaine.comptes.sousdomaine.Eleve;
import com.proflib.rest.dto.CompteCreationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/rien") // ATTENTION a mettre sinon ca ne marche pas!!
public class CreationCompteController {
    Logger logger =  LoggerFactory.getLogger(CreationCompteController.class);
    private Eleve eleve;

    public CreationCompteController() {
    }

    @Inject
    public CreationCompteController(Eleve eleveAggregate) {
        this.eleve = eleveAggregate;
    }

    @POST
    @Path("/creationcompte")
    @Produces
    @Consumes(MediaType.APPLICATION_JSON)
    public void enregistreEtEnvoiMailConfirmation(CompteCreationDTO compteCreationDTO) {
        logger.debug(compteCreationDTO.toString());
        try {
            eleve.creerCompte(compteCreationDTO);
        } catch (Exception e) {
            logger.error("err",e);
        }
    }
    @GET
    @Path("/creationcompteget")
    public String enregistreEtEnvoiMailConfirmationtest() {
        logger.debug("test");
        return "aaaaaaaaaaa";
    }

}
