import java.io.IOException;
import java.util.HashMap;

public class JeuDeTest {
    public static void main(String[] args) throws IOException {
        Lecture a = new Lecture("data/textesimple.txt");
        HashMap coucou = a.trierFreq();
        System.out.println("trie par freq : " + coucou + "\n\n");
        Constructeur test = new Constructeur(a);
        test.testConstruction();

    }
}
