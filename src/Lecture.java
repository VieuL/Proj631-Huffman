import org.apache.commons.lang3.*;
import java.io.FileReader;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Lecture {
    private String ChaineCaracteres;
    private HashMap<Character , Integer> Dictionnaire;
    private BufferedReader read = null;

    // -----------------------------------------------------------------------------------------------------------------

    public Lecture(String ChaineCaracteres) throws IOException {

       this.Dictionnaire = new HashMap<Character, Integer>();

        try {
            this.read = new BufferedReader(new FileReader(ChaineCaracteres));
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
    // -----------------------------------------------------------------------------------------------------------------

    public HashMap<Character,Integer> getDictionnaire(){return  this.Dictionnaire; }

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





}
