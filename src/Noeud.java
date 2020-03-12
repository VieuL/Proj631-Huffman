public class Noeud {

    private Character caractere;
    private int poids;
    private Noeud droit;
    private Noeud gauche;
    private Noeud pere;

    public Noeud(Character caractere,int poids, Noeud gauche, Noeud droit, Noeud pere){
        this.caractere = caractere;
        this.poids = poids;
        this.droit = droit;
        this.gauche = gauche;
        this.pere = pere;
    }

    //Création des getteurs
    public int getPoids(){return this.poids;}
    public Noeud getDroit(){return  this.droit;}
    public Noeud getGauche(){return this.gauche;}
    public Noeud getPere(){return this.pere;}
    public Character getCaractere(){return this.caractere;}

    //Création des setteurs
    public void setPoids(int p){this.poids = p;}
    public void setDroit(Noeud d){this.droit = d;}
    public  void setGauche(Noeud g){this.gauche = g;}
    public void setCaractere(Character caractere) { this.caractere = caractere;}
    public void setPere(Noeud pere) { this.pere = pere;}
}
