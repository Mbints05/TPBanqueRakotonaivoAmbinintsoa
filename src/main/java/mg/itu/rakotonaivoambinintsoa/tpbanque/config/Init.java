/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.rakotonaivoambinintsoa.tpbanque.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author rakot
 */
public class Init {
    
    @Inject
    private GestionnaireCompte gestCompte;
    
    List<CompteBancaire> listcbInit = new ArrayList();
    
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class)
            ServletContext context){
        
        // Ajout de données à la liste
        listcbInit.add(new CompteBancaire("John Lennon", 150000));
        listcbInit.add(new CompteBancaire("Paul McCartney", 950000));
        listcbInit.add(new CompteBancaire("Ringo Starr", 20000));
        listcbInit.add(new CompteBancaire("Georges Harrisson", 100000));
        
        for (CompteBancaire cb : listcbInit) {
            gestCompte.creerCompte(cb);
        }
    }
}
