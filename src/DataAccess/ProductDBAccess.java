package DataAccess;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;


public class ProductDBAccess implements DataAccess {


    @Override
    public void addProduct(Product product) throws addProductException {
        try {
            String sqlInstruction = "insert into product (label, color, price, cost, size, stock, addition_date, is_shippable, information, image_link, category_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getColor());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getCost());
            preparedStatement.setDouble(5, product.getSize());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setDate(7, new java.sql.Date(Date.valueOf(LocalDate.now()).getTime()));
            preparedStatement.setBoolean(8, product.getShippable());
            preparedStatement.setString(9, product.getDescription());
            preparedStatement.setString(10, product.getImgLink());
            preparedStatement.setInt(11, product.getCategory_FK());
            preparedStatement.executeUpdate();
        } catch (SQLException | DBExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editProduct(Product product) throws editProductException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("update product set label = ?, color = ?, price = ?, cost = ?, size = ?, stock = ?, addition_date = ?, is_shippable = ?, information = ?, image_link = ?, category_id = ? where id = ?;");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getColor());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getCost());
            preparedStatement.setDouble(5, product.getSize());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setDate(7, (java.sql.Date) product.getAdditionDate());
            preparedStatement.setBoolean(8, product.getShippable());
            preparedStatement.setString(9, product.getDescription());
            preparedStatement.setString(10, product.getImgLink());
            preparedStatement.setInt(11, product.getCategory_FK());
            preparedStatement.setInt(12, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | DBExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(Product product) throws deleteProductException {
        try {
            String sqlInstruction = "delete from product where id = ?;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | DBExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void searchProduct() throws searchProductException {

    }
    public ArrayList<Category> getAllCategories() throws getAllCategoriesException {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sqlInstruction = "select * from category;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Category category;
            while (data.next()) {
                category = new Category();
                category.setId(data.getInt("id"));
                category.setLabel(data.getString("label"));
                categories.add(category);
            }
        } catch (SQLException | DBExceptions e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public ArrayList<Product> getAllProducts() throws getAllProductsException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sqlInstruction = "select * from product;";
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
                product.setCategory_FK(data.getInt("category_id"));
                products.add(product);
            }
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