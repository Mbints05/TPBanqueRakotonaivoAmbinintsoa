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
import mg.itu.rakotonaivoambinintsoa.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author rakot
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    private List<CompteBancaire> listComptebancaire;

    @Inject
    private GestionnaireCompte gestCompte;
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {}
    
    public List<CompteBancaire> getAllcomptes(){
        if(listComptebancaire == null){
            listComptebancaire = gestCompte.getAllComptes();
        }
        return listComptebancaire;
    }
    
}
