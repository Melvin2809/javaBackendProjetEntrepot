import java.util.LinkedList;

public class Commande {

    private int id;
    private static int count;
    private String nom;
    private Meuble meubles;
    private double prix;
    private String statut;

    public Commande(String nom){
        this.id = count++;
        this.nom = nom;
        this.statut = "en cours";
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Meuble getMeubles() {
        return meubles;
    }

    public void setMeubles(Meuble meubles) {
        this.meubles = meubles;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
