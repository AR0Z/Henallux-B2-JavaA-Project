package Exceptions;

public class GetAllCategoriesException extends Exception {
    public GetAllCategoriesException() {
        super("Il est impossible de récupérer les catégories");
    }
}
