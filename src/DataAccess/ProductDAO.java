package DataAccess;

import Exceptions.*;
import Model.CustomerByProduct;
import Model.Filter;
import Model.Product;
import Model.ProductByFilter;

import java.util.ArrayList;


public interface ProductDAO {
    void addProduct(Product product) throws AddProductException;

    void editProduct(Product product) throws EditProductException;

    void deleteProduct(int id) throws DeleteProductException;

    Product getProductById(int id) throws GetProductByIdException;

    ArrayList<Product> getAllProducts() throws GetAllProductsException;

    ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws GetCustomersWhoPurchasedProductException;

    Boolean isArticleAvailableForDeleting(int id) throws IsArticleAvailableForDeletingException;
    ArrayList<ProductByFilter> getProductsByFilter(Filter filter) throws GetProductsByFilterException;
}
