package Exceptions;

public class DeleteProductException extends Exception {
    public DeleteProductException() {
        super("Il est impossible de supprimer le produit");
    }
}
