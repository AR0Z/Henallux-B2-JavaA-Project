package DataAccess;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;


public class ProductDBAccess implements ProductDAO {


    @Override
    public void addProduct(Product product) throws DBExceptions {
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
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
    }

    @Override
    public void editProduct(Product product) throws DBExceptions {
        try {
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("update product set label = ?, color = ?, price = ?, cost = ?, size = ?, stock = ?, is_shippable = ?, information = ?, image_link = ?, category_id = ? where id = ?;");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getColor());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getCost());
            preparedStatement.setDouble(5, product.getSize());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setBoolean(7, product.getShippable());
            preparedStatement.setString(8, product.getDescription());
            preparedStatement.setString(9, product.getImgLink());
            preparedStatement.setInt(10, product.getCategory_FK());
            preparedStatement.setInt(11, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int id) throws DBExceptions {
        try {
            String sqlInstruction = "delete from product where id = ?;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
    }

    @Override
    public Product getProductById(int id) throws DBExceptions {
        Product product = null;
        try {
            String sqlInstruction = "select * from product where id = ?;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
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
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return product;
    }

    @Override
    public ArrayList<Product> getAllProducts() throws DBExceptions {
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
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return products;
    }
}