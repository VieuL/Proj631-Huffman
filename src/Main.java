import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Lecture du fichier
        Lecture ALire = new Lecture();

        // Création de l'arbre
        Constructeur tree = new Constructeur(ALire);

        //Création du dictionnaire de comparaison
        Encodage encoda = new Encodage(tree);
        System.out.println("-- Encodage terminé --");
        System.out.println("Nombre moyen de bits pour un caractère : " + encoda.nombreBits());
         }
}