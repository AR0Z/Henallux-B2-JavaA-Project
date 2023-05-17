package Exceptions;

public class GetLineByProductException extends Exception {
    public GetLineByProductException() {
        super("Il est impossible de récupérer les lignes du produit");
    }
}
