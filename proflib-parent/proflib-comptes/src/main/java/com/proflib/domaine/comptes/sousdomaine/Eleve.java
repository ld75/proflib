package com.proflib.domaine.comptes.sousdomaine;

import com.proflib.helpers.MailSender;
import com.proflib.repository.IEleveRepository;
import com.proflib.rest.dto.CompteCreationDTO;
import com.proflib.services.EleveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

@Entity
@NamedQuery(name="Eleve.findAll", query="Select e from Eleve e")
@NamedQuery(name="Eleve.findByNomPrenom", query="Select e from Eleve e where e.nom= :nom")
public class Eleve {

    @Transient
    @Inject
    private EleveService eleveService;
    @Transient
    Logger logger =  LoggerFactory.getLogger(Eleve.class);
    @Transient
    @Inject
    IEleveRepository eleveRepository;
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    Adresse adresse;
    @Embedded
    private Prenom prenom;
    @Embedded
    private Nom nom;

    public Eleve(String nom, String prenom) {
        this.nom = new Nom(nom);
        this.prenom=new Prenom(prenom);
        this.adresse=Adresse.VIDE;
    }

    public Eleve(IEleveRepository eleveRepository, EleveService eleveService) {
        this.eleveRepository = eleveRepository;
        this.eleveService = eleveService;
    }

    public Eleve() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getNom() {
        return this.nom.nom;
    }

    public void setEmail(String email) {
    }

    @Override
    public String toString() {
        return this.nom.nom + " " +this.prenom.prenom;
    }
    @Transactional
    public void creerCompte(CompteCreationDTO compteCreationDTO) throws Exception {
        logger.debug(compteCreationDTO.toString());
        Eleve eleve = new Eleve(compteCreationDTO.getNom(),compteCreationDTO.getPrenom());
        eleve.setEmail(compteCreationDTO.getEmail());
        eleveRepository.createEleve(eleve);
        String corpMailTemplate = "Bonjour %s !"+
                    "Merci de vous être inscrit sur Proflib. Votre email est bien %s."+
                    "Si vous avez reçu ce mail par erreur vous pouvez l'ignorer. "+
                    "Pour confirmer votre inscription cliquez ici %s";
        String corpmail  = String.format(corpMailTemplate,compteCreationDTO.getPrenom(), compteCreationDTO.getEmail(),"urlAFAIRE");
        sendMailInscriptionConfirme(compteCreationDTO.getEmail(),"ProfLib - confirmez votre inscription" , corpmail);
    }
    public void sendMailInscriptionConfirme(String email,String sujet, String corpMail) throws Exception {
        MailSender mailSender = new MailSender();
        mailSender.sendTextEmail(Arrays.asList(email),sujet,corpMail);
    }
}