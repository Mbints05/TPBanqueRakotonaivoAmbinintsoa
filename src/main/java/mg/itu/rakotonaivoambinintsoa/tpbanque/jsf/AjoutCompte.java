/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;
import mg.itu.rakotonaivoambinintsoa.tpbanque.util.Util;

/**
 *
 * @author rakot
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    private String nom;

    @PositiveOrZero
    private int solde;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    
    
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
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    @Transactional
    public String action() {
        boolean erreur = false;

        // Contrôle si le nom est vide
        if (this.nom.equalsIgnoreCase("")) {
            Util.messageErreur("Nom requis", "Nom requis", "form:nom");
            erreur = true;
        }

        if (!erreur) {
            this.gestionnaireCompte.creerCompte(new CompteBancaire(this.nom, this.solde));
            Util.addFlashInfoMessage(this.nom + " a été bien créé");
            return "listeComptes?faces-redirect=true";
        } else {
            return null;
        }

    }

}
