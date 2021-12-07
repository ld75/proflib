package com.proflib.controllers;

import com.proflib.command.CreateCompteServiceCommand;
import com.proflib.repository.EleveRepository;
import com.proflib.rest.dto.CompteCreationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/rien") // ATTENTION a mettre sinon ca ne marche pas!!
public class CreationCompteController {
    Logger logger =  LoggerFactory.getLogger(CreationCompteController.class);
    private CreateCompteServiceCommand createCompteCommand;

    public CreationCompteController() {
    }

    @Inject
    public CreationCompteController(CreateCompteServiceCommand createCompteCommand) {
        this.createCompteCommand = createCompteCommand;
    }

    @POST
    @Path("/creationcompte")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void enregistreEtEnvoiMailConfirmation(CompteCreationDTO compteCreationDTO) {
        logger.debug(compteCreationDTO.toString());
        createCompteCommand.creerCompte(compteCreationDTO);

    }
    @GET
    @Path("/creationcompteget")
    public String enregistreEtEnvoiMailConfirmationtest() {
        logger.debug("test");
        return "aaaaaaaaaaa";
    }

}
