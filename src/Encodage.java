import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

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
        encodageTxt();
        tradocte();
    }

    public Noeud getRacine() {
        return racine;
    }

    public HashMap<Character, String> getCodage() {
        return codage;
    }

    /**
     * Cette fonction parcoure l'arbre et implément la HashMap contenant le dictionnaire de passage
     * La HadhMap prends un char et une String
     * @param a La chaine de caractère bianaire
     * @param visit liste des noeud visité par le parcoure
     * @param courant Noeud en cours d'étude
     * @return 0 quand la fonction est terminée
     */
    public int Recherche(String a, ArrayList<Noeud> visit, Noeud courant){
        // Condition d'arret de l'algo
        if(this.constructeur.isEmpty()){
            return 0;
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
        /**
         * Cette fonction écrit dans un document txt dont voici le chemain "data/out/dictionnairePassage.txt".
         * Ce fichier contient l'ensemble des charactères du textes et leurs équivalance binaire
         */
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
        /**
         *Cette fonction a pour but d'encoder un text en Binaire dans un fichier txt
         * Attention les 0 et 1 sont codée en 8btis
         */
        PrintWriter sortie = new PrintWriter("data/out/codage.txt", StandardCharsets.UTF_8);
        BufferedReader t = this.c.getData().getReadSauv();

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
    public void tradocte() throws IOException {
        /**
         * Cette fonction convertie le fichier .txt crée en dessus.
         * Cette fonction regroupe chaque bits en octe et les convertie en code ASCII
         */
        BufferedReader lecture = new BufferedReader(new FileReader("data/out/codage.txt"));
        FileOutputStream sortie = new FileOutputStream("data/out/compre.txt");
        while (true){
            assert lecture != null;
            String ligne = lecture.readLine();
            if (ligne == null){break;}
            String oct = "";
            for(int i=0; i < ligne.length(); i++){
                if (oct.length() < 8 ){
                    oct = oct.concat(String.valueOf(ligne.charAt(i)));
                }
                else {
//                    System.out.println(Integer.parseInt(oct));
                    sortie.write(Integer.parseInt(oct));
                    oct = "";
                }
            }
        }
        lecture.close();
        sortie.close();
    }
}





