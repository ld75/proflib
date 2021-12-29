package com.proflib.controllers;

import com.proflib.dict.TypeCompte;
import com.proflib.domaine.comptes.sousdomaine.Eleve;
import com.proflib.repository.IEleveRepository;
import com.proflib.rest.dto.CompteCreationDTO;
import com.proflib.services.EleveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreationCompteControllerTest {


    @Test
    public void creationCompte_EnregistreEtEnvoiMailConfirmation() {
        final int[] nbmailEnvoye = {0};
        List<String> mailEnvoye= new ArrayList();
        IEleveRepository eleveRepository = new EleveRepositoryMock();
        Eleve eleve = new Eleve(eleveRepository,new EleveService()){
            @Override
            public void sendMailInscriptionConfirme(String email,String sujet, String corpMail) throws IOException {
                nbmailEnvoye[0] =1;
                mailEnvoye.add(corpMail);
            }
        };
        CreationCompteController creationCompteController = new CreationCompteController(eleve);
        CompteCreationDTO comptecreationdto = new CompteCreationDTO("nom", "prenom", "email@email.com", "mdp", "mdp", TypeCompte.ELEVE.getLabel());
        creationCompteController.enregistreEtEnvoiMailConfirmation(comptecreationdto);
        Eleve elevecree = ((EleveRepositoryMock) eleveRepository).getEleveCree();
        Assertions.assertEquals(elevecree.getNom(), "nom");
        Assertions.assertEquals(1, nbmailEnvoye[0]);
        System.out.println("aaaaaaaa" + mailEnvoye);
        Assertions.assertTrue(mailEnvoye.get(0).contains("email@email.com"));
        Assertions.assertTrue(mailEnvoye.get(0).contains("prenom"));
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
