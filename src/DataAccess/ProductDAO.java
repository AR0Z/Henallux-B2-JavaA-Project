package DataAccess;

import Exceptions.*;
import Model.*;

import java.util.ArrayList;


public interface ProductDAO {
    void addProduct(Product product) throws DBExceptions;

    void editProduct(Product product) throws DBExceptions;

    void deleteProduct(int id) throws DBExceptions;

    Product getProductById(int id) throws DBExceptions;

    ArrayList<Product> getAllProducts() throws DBExceptions;

    ArrayList<CustomersWhoPurchasedProduct> getCustomersWhoPurchasedProduct(int id) throws DBExceptions;
}
