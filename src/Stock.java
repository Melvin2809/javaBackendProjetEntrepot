import java.util.LinkedList;
public interface Stock
{

    public void ajouterLot(int strategie, LinkedList lotDePiece, Entrepot entrepot);
    public void retirerLot(Entrepot entrepot);
    public void deplacerLot(Entrepot entrepot);
}
