package DataAccess;

import Exceptions.DBExceptions;
import Model.Customer;
import Model.Purchase;

import java.util.ArrayList;

public interface CustomerDAO {

    Customer getCustomerById(int id) throws DBExceptions;
    ArrayList<Customer> getAllCustomers() throws DBExceptions;
    ArrayList<Purchase> getBoughtHistory(int id) throws DBExceptions;
}
