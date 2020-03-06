import java.io.IOException;
import java.util.HashMap;

public class JeuDeTest {
    public static void main(String[] args) throws IOException {
        Lecture a = new Lecture("data/textesimple.txt");
        HashMap coucou = a.trierFreq();
        HashMap ASCII = a.trierASCII();
        System.out.println("trie par freq : " + coucou);
//        System.out.println("trie par ASCII : " + ASCII);

        Constructeur test = new Constructeur(a);
        test.ConstructionNoeud();
//        System.out.println(test.DeuxPlusPetit());
//        Noeud testN = new Noeud(null,1,null,null,null);
        System.out.println(test.getListeNoeud());
    }
}
