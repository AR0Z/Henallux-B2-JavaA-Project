package Exceptions;

public class GetProductByIdException extends Exception {
    public GetProductByIdException() {
        super("Il est impossible de récupérer le produit");
    }
}
