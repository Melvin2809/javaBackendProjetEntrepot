import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

abstract class Personnel {
   protected int id;
   protected static int count = 0;
   protected String prenom;
   protected String nom;
   protected boolean employe;
   protected int solde;
   protected boolean disponible;
   protected static LinkedList<LotDePieces> construction = new LinkedList<>();

   public Personnel(String prenom,String nom) {
      this.id = count++;
      this.prenom = prenom;
      this.nom = nom;
      this.employe = true;
      this.solde = 0;
      this.disponible = true;
   }

   public Personnel(){

   }

   public void Ajoute_A_ArrayList_Lot_Dans_Espace(Entrepot entrepot, ArrayList<LotDePieces> lotDePieces){
      for(int i = 0; i < entrepot.getEspace().length; i++){
         for(int j = 0; j < entrepot.getN(); j++){
            if (entrepot.getOneEspace(i,j) != null) {
                  lotDePieces.add(entrepot.getOneEspace(i,j));
            }
         }
      }
   }

   protected boolean retireLotDansEspace(Entrepot entrepot,int id,int volume){
      for (int i = 0; i < entrepot.getEspace().length; i++) {
         for (int j = 0; j < entrepot.getN(); j++) {
            if(entrepot.getOneEspace(i,j) != null) {
               if (entrepot.getOneEspace(i, j).getId() == id) {
                  for (int k = 0; k < volume; k++) {
                     entrepot.setOneEspaceToNull(i, j + k);
                  }
                  return true;
               }
            }
         }
      }
      return false;
   }

   protected boolean parcoursEspaceDisponibleEtAjouteLot(Entrepot entrepot, LotDePieces lotDePieces, int volume){
      for(int i = 0; i < entrepot.getEspace().length; i++){
         for(int j = 0; j < entrepot.getN(); j++){
            if (entrepot.getOneEspace(i,j) == null){
               if(volume<=entrepot.getN()-j){
                  for(int k =0;k<volume;k++){
                     entrepot.addLotDePiece(lotDePieces,i,j+k);
                  }
                  return true;
               }
            }
         }
      }
      return false;
   }

   protected ArrayList<int[]> parcoursEspaceDisponible(Entrepot entrepot, int volume){
      ArrayList<int[]> espaceDispo = new ArrayList<>();
      int count = 0;
      for(int i = 0; i < entrepot.getEspace().length; i++){
         for(int j = 0; j < entrepot.getN(); j++){
            if (entrepot.getOneEspace(i,j) == null){
               if(volume<=entrepot.getN()-j){
                  int[] espace = new int[3];
                  espace[0] = count;
                  espace[1] = i;
                  espace[2] = j;
                  count++;
                  espaceDispo.add(espace);
               }
            }
         }
      }
      return espaceDispo;
   }

   public void Salaire(){

   }

   public void Rechercher(){

   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public static int getCount() {
      return count;
   }

   public static void setCount(int count) {
      Personnel.count = count;
   }

   public String getPrenom() {
      return prenom;
   }

   public void setPrenom(String prenom) {
      this.prenom = prenom;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public boolean isEmploye() {
      return employe;
   }

   public void setEmploye(boolean employe) {
      this.employe = employe;
   }

   public int getSolde() {
      return solde;
   }

   public void setSolde(int solde) {
      this.solde = solde;
   }

   public boolean isDisponible() {
      return disponible;
   }

   public void setDisponible(boolean disponible) {
      this.disponible = disponible;
   }

   public static LinkedList<LotDePieces> getConstruction() {
      return construction;
   }

   public static void setConstruction(LinkedList<LotDePieces> construction) {
      Personnel.construction = construction;
   }
}
