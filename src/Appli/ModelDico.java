package Appli;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Roméo Ngoula
 */
public class ModelDico {
    String mot,details;
    //Getters And Setters
    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    //Fin Getters And Setters
    //Constructeurs
    public ModelDico(String mot, String details){
    this.mot = mot;
    this.details = details;
    }
    public ModelDico(ModelDico dico){
    this.mot = dico.mot;
    this.details = dico.details;
    }    

    @Override
    public String toString() {
        return mot + "/" + details;
    }
    //Fonction de chargement
    public  static ArrayList<ModelDico> charge(){
        String chemin = System.getProperty("user.home")+"/TP3_MiniDico/data/minidico.txt";
        ArrayList<ModelDico>  liste = new ArrayList<ModelDico>();
	File Fichier = new File(chemin);						
	//Lecture dans le fichier minidico.txt
	try(FileInputStream user = new  FileInputStream(Fichier)){
            Scanner lect = new Scanner(user);
		
                while(lect.hasNextLine()){
                    String donnees = lect.nextLine();
                    String[] donneesSep = donnees.split("/");
                    ModelDico exam = new ModelDico(donneesSep[0], donneesSep[1]);
                    liste.add(exam);
		}
                
                  lect.close();					
		}catch(IOException e){
                    e.printStackTrace();
		}
        return liste;
                
    }
}
