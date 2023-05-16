package DataAccess;

import Exceptions.DBExceptions;
import Model.CustomerByProduct;
import Model.Filter;
import Model.Product;
import Model.ProductByFilter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class ProductDBAccess implements ProductDAO {
    private Connection connection;
    public ProductDBAccess() {
        try{
            connection = SingletonConnexion.getInstance();
        }catch (DBExceptions exceptions) {
            exceptions.printStackTrace();
        }

    }


    @Override
    public void addProduct(Product product) throws DBExceptions {
        try {
            String sqlInstruction = "insert into product (label, color, price, cost, size, stock, addition_date, is_shippable, information, image_link, category_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                product = new Product(data.getInt("id"), data.getString("label"), data.getString("color"), data.getDouble("price"), data.getDouble("cost"), data.getDouble("size"), data.getInt("stock"), data.getDate("addition_date").toLocalDate(), data.getBoolean("is_shippable"), data.getString("information"), data.getString("image_link"), data.getInt("category_id"));
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
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Product product;
            while (data.next()) {
                product = new Product(data.getInt("id"), data.getString("label"), data.getString("color"), data.getDouble("price"), data.getDouble("cost"), data.getDouble("size"), data.getInt("stock"), data.getDate("addition_date").toLocalDate(), data.getBoolean("is_shippable"), data.getString("information"), data.getString("image_link"), data.getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return products;
    }

    public ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws DBExceptions {
        ArrayList<CustomerByProduct> customerByProduct = new ArrayList<>();
        try {
            String sqlInstruction = "SELECT c.id, c.first_name, c.last_name, l.quantity, o.order_date FROM `customer` c INNER JOIN `order` o on c.id = o.customer_id INNER JOIN `line` l on o.id = l.order_id INNER JOIN `product` p on l.product_id = p.id WHERE p.id = (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            CustomerByProduct customerWhoPurchasedProduct;
            while (data.next()) {
                customerWhoPurchasedProduct = new CustomerByProduct(data.getInt("c.id"), data.getString("c.first_name"), data.getString("c.last_name"), data.getInt("l.quantity"), data.getDate("o.order_date").toLocalDate());
                customerByProduct.add(customerWhoPurchasedProduct);
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return customerByProduct;
    }

    @Override
    public Boolean isArticleAvailableForDeleting(int id) throws DBExceptions {
        boolean isAvailable = false;
        try {
            String sqlInstruction = "select * from line where product_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            if (!data.next()) {
                isAvailable = true;
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return isAvailable;
    }

    @Override
    public ArrayList<ProductByFilter> getProductsByFilter(Filter filter) throws DBExceptions {
        try {
            /*
            * SELECT
    p.id AS product_id,
    p.label AS product_name,
    p.price AS product_price,
    SUM(l.quantity) AS quantity_sold,
    SUM(l.quantity * l.unitary_price) AS total_revenue,
    c.label AS category_name
FROM
    product AS p
    JOIN category AS c ON p.category_id = c.id
    JOIN line AS l ON p.id = l.product_id
    JOIN `order` AS o ON l.order_id = o.id
    JOIN supply AS s ON p.id = s.product_id
    JOIN supplier AS supp ON s.supplier_id = supp.id
WHERE
    supp.label like ? AND
    c.label like ? AND
    o.order_date = '2023-05-14'
GROUP BY
    p.id, p.label, p.price, c.label
ORDER BY


            *
            * */

            String sqlInstruction = "SELECT p.id AS product_id, p.label AS product_name, p.price AS product_price, SUM(l.quantity) AS quantity_sold, SUM(l.quantity * l.unitary_price) AS total_revenue, c.label AS category_name FROM product AS p JOIN category AS c ON p.category_id = c.id JOIN line AS l ON p.id = l.product_id JOIN `order` AS o ON l.order_id = o.id JOIN supply AS s ON p.id = s.product_id JOIN supplier AS supp ON s.supplier_id = supp.id WHERE supp.id LIKE ? AND c.id LIKE ? AND o.order_date BETWEEN ? AND ? GROUP BY p.id, p.label, p.price, c.label ORDER BY ";
            switch (filter.getOrder()) {
                case 0:
                    sqlInstruction += "p.label";
                    break;
                case 1:
                    sqlInstruction += "quantity_sold DESC";
                    break;
                case 2:
                    sqlInstruction += "total_revenue DESC";
                    break;
                case 3:
                    sqlInstruction += "c.label";
                    break;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            if (filter.getSupplier() == null) {
                preparedStatement.setString(1, "%");
            } else {
                preparedStatement.setInt(1, filter.getSupplier().getId());
            }

            if (filter.getCategory() == null) {
                preparedStatement.setString(2, "%");
            } else {
                preparedStatement.setInt(2, filter.getCategory().getId());
            }

            if (!filter.getStartDate().matches("\\d{2}/\\d{2}/\\d{4}")) {
                preparedStatement.setString(3, "2000-01-01");
            } else {
                preparedStatement.setString(3, filter.getFormatedDate(filter.getStartDate()));
            }

            if (!filter.getEndDate().matches("\\d{2}/\\d{2}/\\d{4}")) {
                preparedStatement.setString(4, LocalDate.now().toString());
            } else {
                preparedStatement.setString(4, filter.getFormatedDate(filter.getEndDate()));
            }

            ResultSet data = preparedStatement.executeQuery();
            ArrayList<ProductByFilter> productsByFilter = new ArrayList<>();
            ProductByFilter productByFilter;
            while (data.next()) {
                productByFilter = new ProductByFilter(data.getInt("product_id"), data.getString("product_name"), data.getInt("quantity_sold"), data.getDouble("total_revenue"), data.getString("category_name"));
                productsByFilter.add(productByFilter);
            }
            return productsByFilter;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}