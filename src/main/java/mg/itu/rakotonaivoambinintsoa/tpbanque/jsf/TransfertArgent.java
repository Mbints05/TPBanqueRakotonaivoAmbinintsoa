/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;
import mg.itu.rakotonaivoambinintsoa.tpbanque.util.Util;

/**
 *
 * @author rakot
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {
    
    private CompteBancaire compteBancaire;
    private int idcompteSource;
    private int idcompteDestinataire;
    private int somme;

    public int getCompteSource() {
        return idcompteSource;
    }

    public void setCompteSource(int compteSource) {
        this.idcompteSource = compteSource;
    }                               

    public int getCompteDestinataire() {
        return idcompteDestinataire;
    }

    public void setCompteDestinataire(int compteDestinataire) {
        this.idcompteDestinataire = compteDestinataire;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
    
    @Inject
    private GestionnaireCompte gestCompte;

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }
    
    
    
    public String transfertArgent(){
        boolean erreur = false;
        CompteBancaire compteSource = this.gestCompte.findById(idcompteSource);
        CompteBancaire compteDestinataire = this.gestCompte.findById(idcompteDestinataire);
        
        if (compteSource == null) {
            Util.messageErreur("Aucun compte avec l'id : " + idcompteSource, "Aucun compte avec l'id : " + idcompteSource, "form:idSource");
            erreur = true;
        }if (compteDestinataire == null) {
            Util.messageErreur("Aucun compte avec l'id : " + idcompteDestinataire, "Aucun compte avec l'id : " + idcompteDestinataire, "form:idDestinataire");
            erreur = true;
        }if (compteSource != null && compteSource.getSolde() < somme) {
            Util.messageErreur("Le solde du compte source est insuffisant", "Le solde du compte source est insuffisant", "form:somme");
            erreur = true;
        }if (somme < 0) {
            Util.messageErreur("Le montant saisi est invalide", "Le montant saisi est invalide", "form:somme");
            erreur = true;
        }
        
        if(!erreur){
            gestCompte.transfertArgent(compteSource,compteDestinataire,somme);
            Util.addFlashInfoMessage("Transfert: " + somme + "Ar depuis " + compteSource.getNom() + " vers " + compteDestinataire.getNom() + ".");
            return "listeComptes?faces-redirect=true";
        }else{
            return null;
        }
    }
    
}
