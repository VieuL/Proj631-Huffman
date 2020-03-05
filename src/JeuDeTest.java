import java.io.IOException;
import java.util.HashMap;

public class JeuDeTest {
    public static void main(String[] args) throws IOException {
        Lecture a = new Lecture("data/alice.txt");
        HashMap coucou = a.trierASCII();
        System.out.println(coucou);


    }
}
