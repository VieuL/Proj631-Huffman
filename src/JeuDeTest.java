import java.io.IOException;

public class JeuDeTest {
    public static void main(String[] args) throws IOException {
        // Lecture du fichier "data/textesimple.txt"
        Lecture ALire = new Lecture();

        // Création de l'arbre
        Constructeur tree = new Constructeur(ALire);

        //Création du dictionnaire de comparaison
        Encodage encoda = new Encodage(tree);

         }
}