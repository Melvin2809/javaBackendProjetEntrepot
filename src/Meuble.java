import java.util.*;

public class Meuble
{
    private String nom;
    private String pieceMaison;
    private int dureeConstruction;
    private double prix;
    private boolean vendu;

    public Meuble(String nom, String pieceMaison, int dureeConstruction)
    {
        this.nom = nom;
        this.pieceMaison = pieceMaison;
        this.dureeConstruction = dureeConstruction;
        this.vendu = false;
    }

    public double sumPrixPieces(int vis,int planche,int tissu)
    {
        return this.prix = (vis*0.3+planche*1.99+tissu*2.99);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPieceMaison() {
        return pieceMaison;
    }

    public void setPieceMaison(String pieceMaison) {
        this.pieceMaison = pieceMaison;
    }

    public int getDureeConstruction() {
        return dureeConstruction;
    }

    public void setDureeConstruction(int dureeConstruction) {
        this.dureeConstruction = dureeConstruction;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isVendu() {
        return vendu;
    }

    public void setVendu(boolean vendu) {
        this.vendu = vendu;
    }
}
