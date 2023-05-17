package DataAccess;

import Exceptions.GetAllCustomersException;
import Exceptions.GetBoughtHistoryException;
import Exceptions.GetCustomerByIdException;
import Model.Customer;
import Model.Purchase;

import java.util.ArrayList;

public interface CustomerDAO {

    Customer getCustomerById(int id) throws GetCustomerByIdException;

    ArrayList<Customer> getAllCustomers() throws GetAllCustomersException;

    ArrayList<Purchase> getBoughtHistory(int id) throws GetBoughtHistoryException;
}
