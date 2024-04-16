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

/**
 *
 * @author rakot
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {
    
    private CompteBancaire compteBancaire;
    private int compteSource;
    private int compteDestinataire;
    private int somme;

    public int getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(int compteSource) {
        this.compteSource = compteSource;
    }

    public int getCompteDestinataire() {
        return compteDestinataire;
    }

    public void setCompteDestinataire(int compteDestinataire) {
        this.compteDestinataire = compteDestinataire;
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
    
    /*public CompteBancaire getCompte(int idCompte){
        compteBancaire = gestCompte.findById(idCompte);
        return compteBancaire;
    }*/
    
    public String transfertArgent(){
        gestCompte.transfertArgent(compteSource,compteDestinataire,somme);
        return "listeComptes";
    }
    
}
