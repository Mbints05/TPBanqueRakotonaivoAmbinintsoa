/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.OperationBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author rakot
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private List<OperationBancaire> listOperationsBancaire;
    private int idCompte;
    
    @Inject
    private GestionnaireCompte gestCompte;
    
    private OperationBancaire opBancaire;
    private CompteBancaire compteBancaire;
    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompteBancaire() {
        return this.compteBancaire = gestCompte.findById(idCompte);
    }
    
    public void loadOperation() {
        this.compteBancaire = gestCompte.findById(idCompte);
    }

    public List<OperationBancaire> getAllOperationsBancaire() {
        return this.getCompteBancaire().getOperations();
    }
    
    
    
}
