/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;
import mg.itu.rakotonaivoambinintsoa.tpbanque.util.Util;

/**
 *
 * @author rakot
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private int id;
    private CompteBancaire compteBancaire;

    private String nom;

    @PositiveOrZero
    private int solde;

    @Inject
    private GestionnaireCompte gestCompte;

    public Modification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    /**
     * Méthode action appelée dans le formulaire
     *
     * @return
     */
    public String modifier() {
        boolean erreur = false;
        
        // Contrôle si le nom est vide
        if (nom.equalsIgnoreCase("")) {
            Util.messageErreur("Nom du compte invalide", "Nom du compte invalide", "form:nom");
            erreur = true;
        }
        
        // Création du message en fonction des modifications apportées par l'utilisateur
        String message = "";
        if (compteBancaire.getNom().equals(nom) && compteBancaire.getSolde() == solde) {
            message += "Aucune modification apportée";
        } else {
            message += "Modification réussie : ";
            if (!compteBancaire.getNom().equals(nom)) {
                message += compteBancaire.getNom() + " changé en " + nom;
            }
            if (compteBancaire.getSolde() != solde) {
                message +=" | "+compteBancaire.getSolde() + " Ar changé en " + solde+ " Ar";
            }
        }

        if (!erreur) {
            compteBancaire.setNom(nom);
            compteBancaire.setSolde(solde);
            this.gestCompte.update(compteBancaire);
            Util.addFlashInfoMessage(message);
            return "listeComptes?faces-redirect=true";
        }
        return null;
    }

    public void loadCompte() {
        compteBancaire = gestCompte.findById(id);
        this.nom = this.compteBancaire.getNom();
        this.solde = this.compteBancaire.getSolde();
    }
    
}
