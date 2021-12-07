package com.proflib.command;

import com.proflib.domaine.comptes.sousdomaine.Eleve;
import com.proflib.repository.EleveRepository;
import com.proflib.repository.IEleveRepository;
import com.proflib.rest.dto.CompteCreationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class CreateCompteServiceCommand {
    Logger logger =  LoggerFactory.getLogger(CreateCompteServiceCommand.class);
    private final IEleveRepository eleveStorageMock;

    @Inject
    public CreateCompteServiceCommand(IEleveRepository storageMock) {
        this.eleveStorageMock = storageMock;
    }
    @Transactional
    public void creerCompte(CompteCreationDTO compteCreationDTO) {
        logger.debug(compteCreationDTO.toString());
        Eleve eleve = new Eleve(compteCreationDTO.getNom(),compteCreationDTO.getPrenom());
        eleve.setEmail(compteCreationDTO.getEmail());
        eleveStorageMock.createEleve(eleve);
    }
}
