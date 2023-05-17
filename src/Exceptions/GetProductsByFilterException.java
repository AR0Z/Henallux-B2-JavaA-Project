package Exceptions;

public class GetProductsByFilterException extends Exception {
    public GetProductsByFilterException() {
        super("Il est impossible de récupérer les produits");
    }
}
