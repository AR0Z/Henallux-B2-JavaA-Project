package Exceptions;

public class EditProductException extends Exception {
    public EditProductException() {
        super("Il est impossible de modifier le produit");
    }
}
