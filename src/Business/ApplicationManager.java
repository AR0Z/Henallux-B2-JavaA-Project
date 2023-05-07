package Business;

import DataAccess.ClientDBAccess;
import Exceptions.getAllCustomersException;
import Model.Customer;

import java.util.ArrayList;

public class ApplicationManager {
    private ClientDBAccess clientDBAccess;

    public ApplicationManager() {
        clientDBAccess = new ClientDBAccess();
    }

    public ArrayList<Customer> getAllCustomers() throws getAllCustomersException {
        return clientDBAccess.getAllCustomers();
    }
}
