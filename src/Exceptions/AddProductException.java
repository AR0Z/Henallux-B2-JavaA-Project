package Exceptions;

public class AddProductException extends Exception {
    public AddProductException() {
        super("Il est impossible d'ajouter le produit");
    }
}
