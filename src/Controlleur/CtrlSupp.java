package Controlleur;

import static Appli.ApplicationTP3.delete;
import Appli.ModelDico;
import Vue.FntSupp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Roméo Ngoula
 */
public class CtrlSupp implements ActionListener,WindowListener {
    FntSupp supp;
    public CtrlSupp(FntSupp supp){
        this.supp = supp;  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(supp.getSupprimer())){  
            int a = delete(supp.getChampsupp().getText());
            if(a == -1){
                JOptionPane.showMessageDialog(supp,"Ce Mot n'existe pas dans le MiniDico", "Information"
						,JOptionPane.WARNING_MESSAGE);
            }
            else{
               ArrayList<ModelDico> list2 = ModelDico.charge();
               list2.remove(a);
               JOptionPane.showMessageDialog(supp,"Le mot a été bien supprimé", "Information"
						,JOptionPane.INFORMATION_MESSAGE);
                   
               String chemin = System.getProperty("user.home")+"/TP3_MiniDico/data/minidico.txt";
               File Fichier = new File(chemin);
               try(PrintWriter user = new PrintWriter(new FileOutputStream(Fichier))){
                    if(list2.size()>=1){
                    Object [][]tableau= new Object[list2.size()][3];
                    for(int i=0;i<list2.size();i++){
                        user.println(list2.get(i).getMot()+"/"+list2.get(i).getDetails());                              
                    }     
               }     
               }catch(IOException i){	
                    i.printStackTrace();			
               }
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
        if(source.equals(supp)){
             int resultat = JOptionPane.showConfirmDialog(supp,"Voulez- vous Quitter MiniDico?","Confirmation",JOptionPane.YES_NO_OPTION);
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
