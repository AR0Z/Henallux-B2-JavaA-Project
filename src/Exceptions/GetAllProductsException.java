package Exceptions;

public class GetAllProductsException extends Exception {
    public GetAllProductsException() {
        super("Il est impossible de récupérer les produits");
    }
}
