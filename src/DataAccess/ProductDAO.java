package DataAccess;

import Exceptions.DBExceptions;
import Model.CustomerByProduct;
import Model.Product;

import java.util.ArrayList;


public interface ProductDAO {
    void addProduct(Product product) throws DBExceptions;

    void editProduct(Product product) throws DBExceptions;

    void deleteProduct(int id) throws DBExceptions;

    Product getProductById(int id) throws DBExceptions;

    ArrayList<Product> getAllProducts() throws DBExceptions;

    ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws DBExceptions;

    Boolean isArticleAvailableForDeleting(int id) throws DBExceptions;
}
