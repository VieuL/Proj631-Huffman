import java.util.ArrayList;
import java.util.HashMap;

public class Constructeur {
    private Lecture Data;
    private HashMap<Character, Integer> Dictionnaire;
    private ArrayList<Noeud> listeNoeud;
    // Création des getteurs
    public ArrayList<Noeud> getListeNoeud() {
        return listeNoeud;
    }


    /**
     * Cette classe va servir a construire l'arbre
     *
     * @param Data
     */
    public Constructeur(Lecture Data) {
        this.Data = Data;
        this.Dictionnaire = Data.trierFreq();
        this.listeNoeud = new ArrayList<Noeud>();
        creation();
        creationArbre();
    }


    public Lecture getData() {
        return Data;
    }

    private void creation() {
        for (int i = 0; i < this.Dictionnaire.size(); i++) {
            int premierElementVal = (int) this.Dictionnaire.values().toArray()[i];
            Character premierElementKey = (char) this.Dictionnaire.keySet().toArray()[i];
            Noeud enCours = new Noeud(premierElementKey, premierElementVal, null, null, null);
            this.listeNoeud.add(enCours);
        }
    }


    /**
     * Cette fonction trie la liste
     */
    private void trieListe(){
        ArrayList<Noeud> liste= this.getListeNoeud();
        ArrayList<Noeud> listeT= new ArrayList<Noeud>();
        int a = (int) Float.POSITIVE_INFINITY;
        int index = -1;
        Noeud petit = null;
        while (liste.size()!=0){
            for(Noeud i: liste){
                if(i.getPoids() < a) {
                    a = i.getPoids();
                    petit = i;
                    index = liste.indexOf(i);
                }
            }
            liste.remove(index);
            listeT.add(petit);
             a =(int) Float.POSITIVE_INFINITY;
             index = 0;
             petit = null;
        }
        this.listeNoeud = listeT;
    }


    public void deuxPlusPetit(){
        Noeud premier = this.listeNoeud.get(0);
        Noeud second = this.listeNoeud.get(1);
        Noeud pere = new Noeud(null,(premier.getPoids() + second.getPoids()),premier,second,null);
        this.listeNoeud.add(pere);
        premier.setPere(pere);
        second.setPere(pere);
        this.listeNoeud.remove(premier);
        this.listeNoeud.remove(second);
        trieListe();

    }

    public void creationArbre(){
        while (this.listeNoeud.size() > 1){
            deuxPlusPetit();
        }
    }


    public void afficheArbre(){
        System.out.println(this.getListeNoeud().get(0).getPoids());
        System.out.println(this.getListeNoeud().get(0).getGauche().getPoids() + " | " + this.getListeNoeud().get(0).getDroit().getPoids());
    }
}

