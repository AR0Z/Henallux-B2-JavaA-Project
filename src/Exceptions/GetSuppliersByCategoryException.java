package Exceptions;

public class GetSuppliersByCategoryException extends Exception {
    public GetSuppliersByCategoryException() {
        super("Il est impossible de récupérer les fournisseurs de la catégorie demandée");
    }
}
