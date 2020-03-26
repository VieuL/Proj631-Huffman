import java.io.*;
import java.util.HashMap;
import java.util.*;

public class Lecture {
    private BufferedReader readSauv = null;
    private HashMap<Character , Integer> Dictionnaire;
    private BufferedReader read = null;
    private ArrayList<String> listeChoix;

    /**
     * Constructeur de la classe Lecture
     * @throws IOException
     */
    public Lecture() throws IOException {

       this.Dictionnaire = new HashMap<Character, Integer>();
        this.listeChoix = choix();
        System.out.print("Votre choix : ");
        Scanner user_input = new Scanner( System.in );
        String str = user_input.nextLine();
        String Url = this.listeChoix.get(Integer.parseInt(str));
        try {
            this.read = new BufferedReader(new FileReader(Url));
            this.readSauv = new BufferedReader(new FileReader(Url));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Erreur d'ouverture");
        }

        while (true){

            assert this.read != null;
            String ligne = this.read.readLine();
            if (ligne == null){break;}
            for(int i= 0 ;  i<ligne.length();i++){
                char carac = ligne.charAt(i);
                int index = this.Dictionnaire.getOrDefault((char)carac, 0);
                this.Dictionnaire.put((char)carac, index+1);
            }


        }
        this.read.close();

    }

    public BufferedReader getReadSauv() {
        return readSauv;
    }

    /**
     *
     * @return la HashMap comportant les char ainsi que leurs fréquence
     */
    public HashMap<Character,Integer> getDictionnaire(){return  this.Dictionnaire; }

    /**
     *
     * @return une HashMap trié par ordre croissant des fréquences
     */
    public HashMap<Character, Integer> trierFreq() {

        List linkedlist = new LinkedList(this.Dictionnaire.entrySet());
        Collections.sort(linkedlist, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = linkedlist.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }


    /**
     *
     * @return une HashMap trié par ordre croissant des codes ASCII des chars
     */
    public HashMap<Character, Integer> trierASCII(){
        HashMap<Character,Integer> maHM= new HashMap<Character,Integer>();
        Map<Character, Integer> map = new TreeMap<Character, Integer>(this.Dictionnaire);
        Set set = map.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            char aa = (char)entry.getKey();
            maHM.put((char)(int)aa, (Integer) entry.getValue());
        }
        return maHM;
    }

    public ArrayList<String> choix() throws IOException {
        ArrayList<String> liste = new ArrayList<String>();
        int i =0;
        for(File nom :  new File("data").listFiles()){
            if (nom.getName().contains(".txt")){
                System.out.println("Pour choisir " + nom.getName() + " tapez " + i++);
                liste.add(String.valueOf(nom.getCanonicalFile()));}
        }
        return liste;
    }



}
