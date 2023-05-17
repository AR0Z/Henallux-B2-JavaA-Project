package Exceptions;

public class GetCustomerByIdException extends Exception {
    public GetCustomerByIdException() {
        super("Il est impossible de récupérer le client demandé");
    }
}

