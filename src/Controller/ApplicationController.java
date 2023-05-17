package Controller;

import Business.ApplicationManager;
import Exceptions.*;
import Model.*;

import java.util.ArrayList;

public class ApplicationController {
    private ApplicationManager applicationManager;

    public ApplicationController() throws ConnectionException {
        applicationManager = new ApplicationManager();
    }

    public void closeConnection() throws CloseConnectionException {
        applicationManager.closeConnection();
    }

    public void addProduct(Product product) throws AddProductException, ValueException {
        applicationManager.addProduct(product);
    }

    public void editProduct(Product product) throws EditProductException, ValueException {
        applicationManager.editProduct(product);
    }

    public void deleteProduct(int id) throws DeleteProductException {
        applicationManager.deleteProduct(id);
    }

    public Product getProductById(int id) throws GetProductByIdException {
        return applicationManager.getProductById(id);
    }

    public ArrayList<Product> getAllProducts() throws GetAllProductsException {
        return applicationManager.getAllProducts();
    }

    public Boolean isArticleAvailableForDeleting(int id) throws IsArticleAvailableForDeletingException {
        return applicationManager.isArticleAvailableForDeleting(id);
    }

    public ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws GetCustomersWhoPurchasedProductException {
        return applicationManager.getCustomersWhoPurchasedProduct(id);
    }

    public Category getCategoryById(int id) throws GetCategoryByIdException {
        return applicationManager.getCategoryById(id);
    }

    public ArrayList<Category> getAllCategories() throws GetAllCategoriesException {
        return applicationManager.getAllCategories();
    }
    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws GetSuppliersByCategoryException {
        return applicationManager.getSuppliersByCategory(id);
    }

    public Customer getCustomerById(int id) throws GetCustomerByIdException {
        return applicationManager.getCustomerById(id);
    }

    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException {
        return applicationManager.getAllCustomers();
    }

    public ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException {
        return applicationManager.getAllSuppliers();
    }
    public ArrayList<Purchase> getBoughtHistory(int id) throws GetBoughtHistoryException {
        return applicationManager.getBoughtHistory(id);
    }


    public ArrayList<ProductByFilter> getProductsByFilter(Filter filter) throws GetProductsByFilterException {
        return applicationManager.getProductsByFilter(filter);
    }

    public int getQuantitySold(ArrayList<Line> lines) {
        return applicationManager.getQuantitySold(lines);
    }

    public double getTotalRevenue(ArrayList<Line> lines) {
        return applicationManager.getTotalRevenue(lines);
    }
}
