package Controlleur;

import static Appli.ApplicationTP3.genererDataTab;
import static Appli.ApplicationTP3.saveWord;
import Appli.ModelDico;
import Vue.FntAccueilDico;
import Vue.FntAjout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Roméo Ngoula
 */
public class CtrlAjout implements ActionListener,WindowListener {
    FntAjout ajou;
    public CtrlAjout(FntAjout ajou){
        this.ajou = ajou;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if(source.equals(ajou.getAjout())){
            if(ajou.getTextmot().getText().equals("")|| ajou.getTextdetails().getText().equals("")){
                    JOptionPane.showMessageDialog(ajou,"Un ou plusieurs Champ(s) vide(s)", "Information"
						,JOptionPane.ERROR_MESSAGE);
            }
            else{
                ModelDico dico = new ModelDico(ajou.getTextmot().getText(),ajou.getTextdetails().getText());
                boolean etat = saveWord(dico);
                if(!etat){
                    String chemin = System.getProperty("user.home")+"/TP3_MiniDico/data/minidico.txt";
                    File Fichier = new File(chemin);
                    try(PrintWriter user = new PrintWriter(new FileOutputStream(Fichier,true))){
                        user.println(dico.toString());
                        JOptionPane.showMessageDialog(null,"Le mot "+dico.getMot()+" a été enregistré avec succès", "Information",JOptionPane.INFORMATION_MESSAGE);
                       
                    }catch(IOException i){	
                        i.printStackTrace();			
                    }
                     System.exit(0); 
                } else{	
                    JOptionPane.showMessageDialog(ajou,"Ce Mot existe déja!","Information",JOptionPane.ERROR_MESSAGE);
                    ajou.getTextmot().setText("");
                  } 
               
            }
           
        }
       else{
           int resultat = JOptionPane.showConfirmDialog(ajou,"Voulez- vous fermer MiniDico?","Confirmation",JOptionPane.YES_NO_OPTION);
           if(resultat == JOptionPane.YES_OPTION){
             System.exit(0);
           }
       }
    }

    @Override
    public void windowOpened(WindowEvent e) {
      
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Object source = e.getSource();
        if(source.equals(ajou)){
             int resultat = JOptionPane.showConfirmDialog(ajou,"Voulez- vous Quitter MiniDico?","Confirmation",JOptionPane.YES_NO_OPTION);
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
    
    
}
