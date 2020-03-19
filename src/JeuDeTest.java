import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JeuDeTest {
    public static void main(String[] args) throws IOException {
        // Lecture du fichier "data/textesimple.txt"
        Lecture a = new Lecture();

        // Création de l'arbre
        Constructeur tree = new Constructeur(a);

        //Création du dictionnaire de comparaison
        Encodage dic = new Encodage(tree);

         }
}