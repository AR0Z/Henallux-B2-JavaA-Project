package Exceptions;

public class GetBoughtHistoryException extends Exception {
    public GetBoughtHistoryException() {
        super("Il est impossible de récupérer l'historique d'achat");
    }
}
