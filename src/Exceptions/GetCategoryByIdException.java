package Exceptions;

public class GetCategoryByIdException extends Exception {
    public GetCategoryByIdException() {
        super("Il est impossible de récupérer la catégorie demandée");
    }
}
