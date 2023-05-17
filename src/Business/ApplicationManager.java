package Business;

import DataAccess.*;
import Exceptions.*;
import Model.*;

import java.util.ArrayList;

public class ApplicationManager {
    private ProductDAO productDBAccess;
    private CategorieDAO categorieDBAcces;
    private CustomerDAO customerDBAcces;
    private SupplierDAO supplierDBAccess;
    private DataAccess dbAccess;

    public ApplicationManager() throws ConnectionException {
        productDBAccess = new ProductDBAccess();
        categorieDBAcces = new CategorieDBAcces();
        customerDBAcces = new CustomerDBAcces();
        supplierDBAccess = new SupplierDBAccess();
        dbAccess = new DBAccess();
    }

    public void closeConnection() throws CloseConnectionException {
        dbAccess.closeConnection();
    }

    // Product methods

    public void addProduct(Product product) throws AddProductException {
        productDBAccess.addProduct(product);
    }

    public void editProduct(Product product) throws EditProductException {
        productDBAccess.editProduct(product);
    }

    public void deleteProduct(int id) throws DeleteProductException {
        productDBAccess.deleteProduct(id);
    }

    public Product getProductById(int id) throws GetProductByIdException {
        return productDBAccess.getProductById(id);
    }

    public ArrayList<Product> getAllProducts() throws GetAllProductsException {
        return productDBAccess.getAllProducts();
    }

    public ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws GetCustomersWhoPurchasedProductException {
        return productDBAccess.getCustomersWhoPurchasedProduct(id);
    }

    public Boolean isArticleAvailableForDeleting(int id) throws IsArticleAvailableForDeletingException {
        return productDBAccess.isArticleAvailableForDeleting(id);
    }

    // Category methods

    public Category getCategoryById(int id) throws GetCategoryByIdException {
        return categorieDBAcces.getCategoryById(id);
    }

    public ArrayList<Category> getAllCategories() throws GetAllCategoriesException {
        return categorieDBAcces.getAllCategories();
    }
    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws GetSuppliersByCategoryException {
        return categorieDBAcces.getSuppliersByCategory(id);
    }

    // Customer methods

    public Customer getCustomerById(int id) throws GetCustomerByIdException {
        return customerDBAcces.getCustomerById(id);
    }

    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException {
        return customerDBAcces.getAllCustomers();
    }
    public ArrayList<Purchase> getBoughtHistory(int id) throws GetBoughtHistoryException {
        return customerDBAcces.getBoughtHistory(id);
    }

    // Supplier methods
    public ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException {
        return supplierDBAccess.getAllSuppliers();
    }

    public ArrayList<ProductByFilter> getProductsByFilter(Filter filter) throws GetProductsByFilterException {
        return productDBAccess.getProductsByFilter(filter);
    }

    public int getQuantitySold(ArrayList<Line> lines) {
        int quantity = 0;
        for (Line line : lines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getTotalRevenue(ArrayList<Line> lines) {
        double total = 0;
        for (Line line : lines) {
            total += line.getPrice() * line.getQuantity();
        }
        return total;
    }
}
