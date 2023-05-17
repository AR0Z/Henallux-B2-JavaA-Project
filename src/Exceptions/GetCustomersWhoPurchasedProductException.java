package Exceptions;

public class GetCustomersWhoPurchasedProductException extends Exception {
    public GetCustomersWhoPurchasedProductException() {
        super("Il est impossible de récupérer les clients ayant acheté le produit");
    }
}
