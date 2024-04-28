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
import java.util.logging.Logger;
import mg.itu.rakotonaivoambinintsoa.tpbanque.entity.CompteBancaire;
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author rakot
 */
public class Init {

    
    @Inject
    private GestionnaireCompte gestCompte;
    
    private final static Logger logger = Logger.getLogger("mg.itu.rakotonaivoambinintsoa.tpbanque.Init");
    
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        
        if (this.gestCompte.compterComptes() != 0) {
            logger.info("La table contient des données");
            return;
        }
        
        CompteBancaire john = new CompteBancaire("John Lennon", 150000);
        CompteBancaire paul = new CompteBancaire("Paul McCartney", 950000);
        CompteBancaire ringo = new CompteBancaire("Ringo Starr", 20000);
        CompteBancaire georges = new CompteBancaire("Georges Harrisson", 100000);

        this.gestCompte.creerCompte(john);
        this.gestCompte.creerCompte(paul);
        this.gestCompte.creerCompte(ringo);
        this.gestCompte.creerCompte(georges);

    }
}
