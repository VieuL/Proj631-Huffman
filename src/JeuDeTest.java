import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JeuDeTest {
    public static void main(String[] args) throws IOException {
        // Lecture du fichier
        Lecture a = new Lecture("data/textesimple.txt");

        // Création de l'arbre
        Constructeur tree = new Constructeur(a);

        //Création du dictionnaire de comparaison
        Encodage dic = new Encodage(tree);
        dic.encodageTxt();
    }
}