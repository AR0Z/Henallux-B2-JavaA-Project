package Exceptions;

public class GetAllCustomersException extends Exception {
    public GetAllCustomersException() {
        super("Il est impossible de récupérer la liste des clients");
    }
}
