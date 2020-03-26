import java.util.ArrayList;
import java.util.HashMap;

public class Constructeur {
    private Lecture data;
    private HashMap<Character, Integer> dictionnaire;
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
        this.data = Data;
        this.dictionnaire = Data.trierFreq();
        this.listeNoeud = new ArrayList<Noeud>();
        creation();
        creationArbre();
    }


    public Lecture getData() {
        return data;
    }


    /**
     * La fonction creation crée pour chaque caractères un noeud son poids correspond à sa fréquence.
     */
    private void creation() {
        for (int i = 0; i < this.dictionnaire.size(); i++) {
            int premierElementVal = (int) this.dictionnaire.values().toArray()[i];
            Character premierElementKey = (char) this.dictionnaire.keySet().toArray()[i];
            Noeud enCours = new Noeud(premierElementKey, premierElementVal, null, null, null);
            this.listeNoeud.add(enCours);
        }
    }


    /**
     * Cette fonction trie la liste dans l'ordre croissant du poids des noeuds.
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


    /**
     * Fonction qui récupère les deux noeuds avec le poids le plus faible. Elle crée ensuite leurs père. Pour finir il faur supprimer les noeuds fils de la liste
     */
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

    /**
     * On exécute la fonction deuxPlusPetit jusqu'a avoir qu'un seul noeuds dans la liste
     */
    public void creationArbre(){
        while (this.listeNoeud.size() > 1){
            deuxPlusPetit();
        }
    }


    /**
     * Fonction qui affiche le premiere etage de l'arbre
     */
    public void afficheArbre(){
        System.out.println(this.getListeNoeud().get(0).getPoids());
        System.out.println(this.getListeNoeud().get(0).getGauche().getPoids() + " | " + this.getListeNoeud().get(0).getDroit().getPoids());
    }
}

