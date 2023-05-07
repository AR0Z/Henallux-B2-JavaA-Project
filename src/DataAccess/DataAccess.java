package DataAccess;

import Exceptions.*;
import Model.*;

import java.util.ArrayList;


public interface DataAccess {
    void addProduct(Product product) throws addProductException;

    void editProduct(Product product) throws editProductException;

    void deleteProduct(Product product) throws deleteProductException;

    void searchProduct() throws searchProductException;

    ArrayList<Product> getAllProducts() throws getAllProductsException;
    ArrayList<Category> getAllCategories() throws getAllCategoriesException;

    void searchWhoBought() throws searchWhoBoughtException;

    void searchWhoSupplied() throws searchWhoSuppliedException;

    ArrayList<SearchBoughtHistory> searchBoughtHistory(Customer customer) throws searchBoughtHistoryException;

    void showStatistics() throws showStatisticsException;
}
