package com.proflib.controllers;

import com.proflib.command.CreateCompteServiceCommand;
import com.proflib.dict.TypeCompte;
import com.proflib.domaine.comptes.sousdomaine.Eleve;
import com.proflib.repository.IEleveRepository;
import com.proflib.rest.dto.CompteCreationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreationCompteControllerTest {


    @Test
    public void creationCompte_EnregistreEtEnvoiMailConfirmation() {
        IEleveRepository storageMock = new EleveRepositoryMock();
        CreationCompteController creationCompteController = new CreationCompteController(new CreateCompteServiceCommand(storageMock));
        CompteCreationDTO comptecreationdto = new CompteCreationDTO("nom", "prenom", "email@email.com", "mdp", "mdp", TypeCompte.ELEVE.getLabel());
        creationCompteController.enregistreEtEnvoiMailConfirmation(comptecreationdto);
        Eleve elevecree = ((EleveRepositoryMock) storageMock).getEleveCree();
        Assertions.assertEquals(elevecree.getNom(), "nom");
    }

    private class EleveRepositoryMock implements IEleveRepository {
        private Eleve elevecree;

        public Eleve getEleveCree() {
            return this.elevecree;
        }

        @Override
        public void createEleve(Eleve eleve) {
            this.elevecree = eleve;
        }
    }
}
