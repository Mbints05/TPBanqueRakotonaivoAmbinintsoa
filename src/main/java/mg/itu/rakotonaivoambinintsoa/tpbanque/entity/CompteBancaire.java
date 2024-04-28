/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rakot
 */
@Entity
@NamedQuery(name = "comptebancaire.findAll", query = "SELECT c FROM CompteBancaire c")
@NamedQuery(name = "comptebancaire.count", query = "SELECT count(c) FROM CompteBancaire c")
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private int solde;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OperationBancaire> opBancaire = new ArrayList();
    
    public CompteBancaire() {
    }
    
    
    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        opBancaire.add(new OperationBancaire("Création de comtpe",solde));
    }
    
    public List<OperationBancaire> getOperations(){
        return this.opBancaire;
    }

    public void deposer(int montant) {
        solde += montant;
        opBancaire.add(new OperationBancaire("Crédit",montant));
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
            opBancaire.add(new OperationBancaire("Débit",-montant));
        } else {
            solde = 0;
        }
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire[ id=" + id + " ]";
    }

}
