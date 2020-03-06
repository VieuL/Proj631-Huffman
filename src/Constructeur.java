import java.util.ArrayList;
import java.util.HashMap;

public class Constructeur {
    private Lecture Data;
    private HashMap<Character,Integer> Dictionnaire;
    private ArrayList<Noeud> listeNoeud;

    // Création des getteurs
    public ArrayList<Noeud> getListeNoeud() {
        return listeNoeud;
    }

    /**
     * Cette classe va servir a construire l'arbre
     * @param Data
     */
    public Constructeur(Lecture Data){
        this.Data = Data;
        this.Dictionnaire = Data.trierFreq();
        this.listeNoeud = new ArrayList<Noeud>();
    }

    /**
     *
     * @return Retourne une HashMap<Character,Integer> qui contient les deux plus petit noeuds de la liste de noeuds ou du dic
     */
    public HashMap<Character,Integer> DeuxPlusPetit(){
        HashMap<Character,Integer> aRetourner = new HashMap<Character,Integer>();

        // Récupération des deux premières valeurs de  la liste
        int premierElementVal = (int) this.Dictionnaire.values().toArray()[0];
        int deuxiemeElementVal = (int) this.Dictionnaire.values().toArray()[1];

        Character premierElementKey = (char) this.Dictionnaire.keySet().toArray()[0];
        Character deuxiemeElementKey = (char) this.Dictionnaire.keySet().toArray()[1];

        int changement1 = 0;
        int changement2 = 0;

        // Nous regardons si il n'y a pas un noeud avec une valeurs plus petit dans la liste
        for (Noeud i : this.listeNoeud){

            if ((i.getPoids() < premierElementVal) && (i.getPere() == null)){
                premierElementVal = i.getPoids();
                premierElementKey = null;
                changement1 = 1;
            }

            if ((i.getPoids() < deuxiemeElementVal) && (i.getPere() == null)){
                deuxiemeElementVal = i.getPoids();
                deuxiemeElementKey = null;
                changement2 = 1;
            }

        }

        // Supp des plus petit valeur de Dictionnaire
        if (changement1 == 0){
            this.Dictionnaire.remove(premierElementKey);
        }

        if (changement2 == 0){
            this.Dictionnaire.remove(deuxiemeElementKey);
        }

    aRetourner.put(premierElementKey,premierElementVal);
    aRetourner.put(deuxiemeElementKey,deuxiemeElementVal);
    return aRetourner;

    }


    public void ConstructionNoeud(){
        HashMap<Character,Integer> plusPetit = DeuxPlusPetit();
        int premierElementVal = (int) plusPetit.values().toArray()[0];
        int deuxiemeElementVal = (int) plusPetit.values().toArray()[1];

        Character premierElementKey = (char) plusPetit.keySet().toArray()[0];
        Character deuxiemeElementKey = (char) plusPetit.keySet().toArray()[1];

        Noeud first = null;
        Noeud sec = null;
        if (premierElementKey != null){
             first = new Noeud(premierElementKey,premierElementVal,null,null,null);
            this.listeNoeud.add(first);
        }
        if (deuxiemeElementKey != null){
             sec = new Noeud(deuxiemeElementKey,deuxiemeElementVal,null,null,null);
            this.listeNoeud.add(sec);
        }

        if (premierElementKey == null){
             first = new Noeud(null,premierElementVal,null,null,null);
            this.listeNoeud.add(first);
        }
        if (deuxiemeElementKey == null){
             sec = new Noeud(null,deuxiemeElementVal,null,null,null);
            this.listeNoeud.add(sec);
        }

        // Création du noeud père

        this.listeNoeud.add(new Noeud(null,(premierElementVal + deuxiemeElementVal),sec,first,null));

    }
}

