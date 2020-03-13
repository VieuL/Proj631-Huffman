import java.util.ArrayList;
import java.util.HashMap;

public class Encodage {
    private Noeud racine;
    private ArrayList<Noeud> constructeur;
    private HashMap<Character, String> codage = new HashMap<Character, String>();

    public Encodage(ArrayList<Noeud> constructeur){
        this.constructeur = constructeur;
        this.racine = constructeur.get(0);
    }

    public int Recherche(String a, ArrayList<Noeud> visit){
        // Condition d'arret de l'algo
        if(this.constructeur == null ){
            return 1;
        }
        else{
            // Si il y a un fils droit alors
            if(racine.getDroit() != null && !visit.contains(racine)){
                a = a+"0";
            }
            // Si il a un fils gauche mais pas de fils droit
            else if(racine.getGauche() != null && !visit.contains(racine)){
                a = a+"1";
            }

            // Si il n'as pas de fils
            else {
                if (racine.getCaractere() == null){
                    a.substring(0,a.length()-1);

                }
            }
        }
        return 1;
    }

}
