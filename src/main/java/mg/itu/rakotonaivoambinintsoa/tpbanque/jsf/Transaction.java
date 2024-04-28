/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.persistence.OptimisticLockException;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
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
@Named(value = "transaction")
@ViewScoped
public class Transaction implements Serializable {

    private int id;
    private CompteBancaire compte;
    private String typeTransaction;
    
    @PositiveOrZero
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    }

    /**
     * Méthode validatrice pour le montant du mouvement. Remarque : La méthode
     * doit toujours avoir cette signature.
     *
     * @param fc
     * @param composant le composant JSF dans lequel on valide.
     * @param valeur valeur à valider (le montant pour ce cas)
     */
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement");
        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement == null) {
            return;
        }
        if (valeurTypeMouvement.equals("retrait")) {
            int retrait = (int) valeur;
            if (compte.getSolde() < retrait) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Le retrait doit être inférieur au solde du compte",
                                    "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    public String enregistrerMouvement() {
        try{
            if (typeTransaction.equals("ajout")) {
                gestionnaireCompte.deposer(compte, montant);
            } else {
                gestionnaireCompte.retirer(compte, montant);
            }
            Util.addFlashInfoMessage("Mouvement enregistré sur le compte de " + compte.getNom());
            return "listeComptes?faces-redirect=true";
        
        }catch(OptimisticLockException ex){
            Util.messageErreur("Le compte de " + compte.getNom() + " modifié par un autre utilisateur,rééssayer");

            this.loadCompte();
            return null;
        }
        
    }

    /**
     * Creates a new instance of Mouvement
     */
    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}
