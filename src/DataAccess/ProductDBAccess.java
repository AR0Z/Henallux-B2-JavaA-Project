package DataAccess;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;
import java.sql.*;


public class ProductDBAccess implements DataAccess {


    @Override
    public void addProduct() throws addProductException {

    }

    @Override
    public void editProduct() throws editProductException {

    }

    @Override
    public void deleteProduct() throws deleteProductException {

    }

    @Override
    public void searchProduct() throws searchProductException {

    }
    public ArrayList<Category> getAllCategories() throws getAllCategoriesException {
        ArrayList<Category> categories = new ArrayList<>();

        return categories;
    }

    @Override
    public ArrayList<Product> getAllProducts() throws getAllProductsException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sqlInstruction = "select * from product";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Product product;
            while (data.next()) {
                product = new Product();
                product.setId(data.getInt("id"));
                product.setName(data.getString("label"));
                product.setColor(data.getString("color"));
                product.setPrice(data.getDouble("price"));
                product.setCost(data.getDouble("cost"));
                product.setSize(data.getDouble("size"));
                product.setStock(data.getInt("stock"));
                product.setAdditionDate(data.getDate("addition_date"));
                product.setShippable(data.getBoolean("is_shippable"));
                product.setDescription(data.getString("information"));
                product.setImgLink(data.getString("image_link"));
                products.add(product);
            }
            connection.close();
        } catch (DBExceptions e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void searchWhoBought() throws searchWhoBoughtException{

    }

    @Override
    public void searchWhoSupplied() throws searchWhoSuppliedException {

    }

    @Override
    public void searchBoughtHistory() throws searchBoughtHistoryException {

    }

    @Override
    public void showStatistics() throws showStatisticsException {

    }
}