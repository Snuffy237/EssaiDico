package Appli;

import Vue.FntAccueilDico;
import static Vue.FntAccueilDico.lister;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Roméo Ngoula
 */
public class ApplicationTP3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createFile();
        //saveWordDefault();
        try 
        {    //Look And Feel pour la propriété Systeme
             //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.setLookAndFeel(new NimbusLookAndFeel());
             //SwingUtilities.updateComponentTreeUI(null);
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
        FntAccueilDico Acc = new FntAccueilDico();
        
    }
    
    //
    public static void createFile(){
         //Définitions des variables pour création du Dossier contenant le fichier utilisateur.txt 
        String sep = System.getProperty("file.separator");
	String path = System.getProperty("user.home");
	path = path + sep + "TP3_MiniDico" + sep + "data";
	String path1 = path+ sep + "minidico.txt";
       
        
        //Création des Dossiers
        File f = new File(path);
        File Fichier = new File(path1);
	if(!f.exists()){			
            f.mkdirs();            
	}
        
        if(!Fichier.exists()){
				
	try {
		Fichier.createNewFile();
            } catch (IOException e) {
		e.printStackTrace();
        	}
         }
      
    }
    //Fonction pour enregistrer les Utilisateurs		
    public static boolean saveWord(ModelDico dico){
        boolean etat_e = false;
	String chemin = System.getProperty("user.home")+"/TP3_MiniDico/data/minidico.txt";		
        //Création du Fichier Utilisateur.txt
	File Fichier = new File(chemin);
	//Ecrire dans le fichier Utilisateurs.txt		
	try(FileInputStream user = new  FileInputStream(Fichier); 
            Scanner lect = new Scanner(user)){				
            while(lect.hasNextLine()){			  		
		String donnees = lect.nextLine();
		String[] donneesSep = donnees.split("/");
		if(donneesSep[0].equalsIgnoreCase(dico.getMot())){
                    etat_e = true;
		}
                else{
                    etat_e = false;
                }
             }
         }catch(IOException e){
            e.printStackTrace();
         }	
         return etat_e;
			
    }
    //generer les élements du tableau
    public static void genererDataTab(){    
        ArrayList<ModelDico> list1 = ModelDico.charge();
        if(list1.size()>=1){
            Object [][]tableau= new Object[list1.size()][3];
            int a = 1;
            for(int i=0;i<list1.size();i++){
                tableau[i][0] = a++;
                tableau[i][1] = list1.get(i).getMot();
                tableau[i][2] = list1.get(i).getDetails();                            
            }
        lister(tableau);     
     }
 } 
 //Enregistre les mots par défaut dans le fichier
 public static void saveWordDefault(){
     String chemin = System.getProperty("user.home")+"/TP3_MiniDico/data/minidico.txt";
     File Fichier = new File(chemin);
     ModelDico dico1 = new ModelDico("Lire","Suivre des yeux les caractères d'une écriture et pouvoir les identifier");
     ModelDico dico2 = new ModelDico("Ecrire","Tracer des signes d'écriture, un ensemble organisé de ces signes");
     ModelDico dico3 = new ModelDico("Chanter","Former avec la voix une suite de sons musicaux.");
     ModelDico dico4 = new ModelDico("Danser","Exécuter une danse; se mouvoir avec rythme, en accord avec une musique, un type de mouvement réglé");
     
     boolean su = saveWord(dico1);
     boolean su1 = saveWord(dico2);
     boolean su2 = saveWord(dico3);
     boolean su3 = saveWord(dico4);
  
     if(!su && !su1 && !su2 && !su3){
        try(PrintWriter user = new PrintWriter(new FileOutputStream(Fichier,true))){
            user.println(dico1.toString());
            user.println(dico2.toString());
            user.println(dico3.toString());
            user.println(dico4.toString());
        }catch(IOException e){	
            e.printStackTrace();			
        }
     }
     else{
         System.out.println("Ces mots existent déja");
     }
 }
 //Fonction de Recherche
 public static Object[][] search(String rech){
      ArrayList<ModelDico> list2 = ModelDico.charge();
      boolean etatR = false;
      Object[][] infos = new Object[1][3];
      int a;
      Iterator it = list2.iterator();	
      ModelDico res = null;
      while(it.hasNext()){
    	res = (ModelDico) it.next();
        
    	if(res.getMot().equalsIgnoreCase(rech)){
           infos[0][0] = list2.indexOf(res)+1;
           infos[0][1] = res.getMot();
           infos[0][2] = res.getDetails();
           etatR = true;
           break;	
    	}
      }
    	return infos;  
        
 }
 
 //Fonction pour Supprimer
  public static int delete(String rech){
      ArrayList<ModelDico> list2 = ModelDico.charge();
      int a = -1;
      Iterator it = list2.iterator();	
      ModelDico res = null;
      while(it.hasNext()){
    	res = (ModelDico) it.next();
        
    	if(res.getMot().equalsIgnoreCase(rech)){
           a = list2.indexOf(res);
           break;	
    	}
      }
    	return a;  
        
 }
}
