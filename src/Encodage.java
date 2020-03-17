import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Encodage {
    private Noeud racine;
    private ArrayList<Noeud> constructeur;
    private HashMap<Character, String> codage = new HashMap<Character, String>();
    private Constructeur c;

    public Encodage(Constructeur c) throws IOException {
        this.c = c;
        this.constructeur = c.getListeNoeud();
        this.racine = constructeur.get(0);
        creationDic();

    }

    public Noeud getRacine() {
        return racine;
    }

    public HashMap<Character, String> getCodage() {
        return codage;
    }

    public int Recherche(String a, ArrayList<Noeud> visit, Noeud courant){
        // Condition d'arret de l'algo
        if(this.constructeur.isEmpty()){
            return 1;
        }
        else{
            // Si il y a un fils droit
            if(courant.getDroit() != null && visit.contains(courant.getDroit()) == false){
                a = a+"0";
                return Recherche(a,visit, courant.getDroit());
            }
            // Sinon si il a un fils gauche
            else if(courant.getGauche() != null && visit.contains(courant.getGauche()) == false){
                a = a+"1";
                return Recherche(a,visit,courant.getGauche());
            }

            // Si il n'as pas de fils

            else if ((courant.getCaractere() == null && courant.getDroit() == null && courant.getGauche() == null) ||
                    (courant.getCaractere() == null && visit.contains(courant.getDroit()) && visit.contains(courant.getGauche()))){
                    if (a.isEmpty()){a = "";}
                    else {a = a.substring(0,a.length()-1);}
                    this.constructeur.remove(courant);
                    visit.add(courant);
                    return Recherche(a,visit, courant.getPere());

                }
            else {
//                    System.out.println("Ma lettre est " + courant.getCaractere() + " Mon code est " + a);
                    this.codage.put(courant.getCaractere(),a);
                    visit.add(courant);
                    this.constructeur.remove(courant);
                    if (a.isEmpty()){a = "";}
                    else {a = a.substring(0,a.length()-1);}
                    return Recherche(a,visit, courant.getPere());

                }
            }
        }



    public void creationDic() throws IOException {

        // Création d'une hashmap avec l'ensemble des char ainsi que leurs équivalance en binaire
        String mons = "";
        ArrayList<Noeud> array= new ArrayList<Noeud>();
        Recherche(mons, array, this.getRacine());
        HashMap<Character, String> alphabet = this.getCodage();

        // Ecriture dans un fichier text
        PrintWriter writer = new PrintWriter("data/out/dictionnairePassage.txt", StandardCharsets.UTF_8);
        for (int i = 0; i < alphabet.size(); i++){
            String premierElementVal = (String) alphabet.values().toArray()[i];
            Character premierElementKey = (char) alphabet.keySet().toArray()[i];
            String ecrire = premierElementKey + " " + premierElementVal;
            writer.println(ecrire);
        }
        writer.close();
        }


    public void encodageTxt() throws IOException {
        PrintWriter sortie = new PrintWriter("data/out/codage.txt", StandardCharsets.UTF_8);
        BufferedReader t = this.c.getData().getChaineCaracteres();

        while (true){

            assert t != null;
            String ligne = t.readLine();
            if (ligne == null){break;}
            for(int i= 0 ;  i<ligne.length();i++){
                Character carac = ligne.charAt(i);

                String data = this.codage.get(carac);
                sortie.print(data);
//                sortie.write(Integer.parseInt(data));
            }


        }

        t.close();
        sortie.close();
    }
}





