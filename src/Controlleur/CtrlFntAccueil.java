package Controlleur;

import static Appli.ApplicationTP3.genererDataTab;
import static Appli.ApplicationTP3.search;
import Vue.FntAccueilDico;
import static Vue.FntAccueilDico.lister;
import Vue.FntAjout;
import Vue.FntSupp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Roméo Ngoula
 */
public class CtrlFntAccueil implements WindowListener,ActionListener{
    FntAccueilDico acc;
    public CtrlFntAccueil(FntAccueilDico acc){
        this.acc = acc;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Object source = e.getSource();
        if(source.equals(acc)){
             int resultat = JOptionPane.showConfirmDialog(acc,"Voulez- vous fermer le MiniDico?","Confirmation",JOptionPane.YES_NO_OPTION);
             if(resultat == JOptionPane.YES_OPTION){
             System.exit(0);
             }
        }
       
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
      
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source.equals(acc.getInsere())){
           acc.dispose();
           new FntAjout();
       }
       else if(source.equals(acc.getQuitter())){
           int resultat = JOptionPane.showConfirmDialog(acc,"Voulez- vous fermer le MiniDico?","Confirmation",JOptionPane.YES_NO_OPTION);
           if(resultat == JOptionPane.YES_OPTION){
           System.exit(0);
           }
       }
       else if(source.equals(acc.getRechercher())){
           String requete = acc.getChamprech().getText();
           lister(search(requete));
       }
       else if(source.equals(acc.getSupprimer())){
           acc.dispose();
           new FntSupp();
       }
       
    }
    
}
