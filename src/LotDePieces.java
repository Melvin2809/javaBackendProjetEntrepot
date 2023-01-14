import java.util.ArrayList;

public class LotDePieces {
    private int id;
    private static int count = 1;
    private static int nbPieces;
    private String nomLot;
    private ArrayList<Pieces> pieces = new ArrayList<>();
    private int volume;

    public LotDePieces() {
    }

    public LotDePieces(String nomLot, int volume) {
        this.id = count;
        count++;

        this.nomLot = nomLot;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "LotDePieces{" +
                "id=" + id +
                ", nomLot='" + nomLot + '\'' +
                ", pieces=" + pieces +
                ", volume=" + volume +
                '}';
    }

    public void addPieces(String nom, double prix, double poids) {
        pieces.add(new Pieces(nom, prix, poids));
    }

    // Getters ***************************************


    public int getId() {
        return id;
    }

    public String getNomLot() {
        return nomLot;
    }

    public ArrayList<Pieces> getPieces() {
        return pieces;
    }

    public Pieces getPiece(int i) {
        return pieces.get(i);
    }

    public int getVolume() {
        return volume;
    }

    // Setters ****************************************

    public void setId(int id) {
        this.id = id;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public class Pieces {
        private String nom;

        private double prix;
        private double poids;

        public Pieces(String nom, double prix, double poids) {
            this.nom = nom;
            this.prix = prix;
            this.poids = poids;
        }

        public String getNom() {
            return nom;
        }

        public double getPoids() {
            return poids;
        }

        public double getPrix() {
            return prix;
        }
    }
}
