/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;

/**
 *
 * @author rakot
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="rakoto05", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)
@ApplicationScoped
public class GestionnaireCompte {
    
    //l'EntityManager (c'est l'annotation @PersistenceContext qui provoquera l'injection)
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }
    
    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("comptebancaire.findAll");
        return query.getResultList();
    }
    
    //Chercher par ID
    public CompteBancaire findById(int idCompte) {
        return em.find(CompteBancaire.class, idCompte);
    }
    
    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    @Transactional
    public void transfertArgent(CompteBancaire compteSource,CompteBancaire compteDestinataire,int somme){
        //Retrait source
        compteSource.retirer(somme);
        compteDestinataire.deposer(somme);
        
        //Update
        this.update(compteSource);
        this.update(compteDestinataire);
        
    }
    
    /**
     * Dépôt d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }

    /**
     * Retrait d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }
}
