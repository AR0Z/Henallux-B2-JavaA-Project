package Exceptions;

public class GetAllSuppliersException extends Exception {
    public GetAllSuppliersException() {
        super("Il est impossible de récupérer les fournisseurs");
    }
}
