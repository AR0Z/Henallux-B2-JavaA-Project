package DataAccess;

import Exceptions.*;
import Model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.CompletionException;


public class ProductDBAccess implements ProductDAO {
    private Connection connection;
    public ProductDBAccess() throws ConnectionException  {
        connection = SingletonConnexion.getInstance();
    }


    @Override
    public void addProduct(Product product) throws AddProductException {
        try {
            String sqlInstruction = "insert into product (label, color, price, cost, size, stock, addition_date, is_shippable, information, image_link, category_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getColor());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getCost());
            preparedStatement.setDouble(5, product.getSize());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setDate(7, new java.sql.Date(Date.valueOf(product.getAdditionDate()).getTime()));
            preparedStatement.setBoolean(8, product.getShippable());
            preparedStatement.setString(9, product.getDescription());
            preparedStatement.setString(10, product.getImgLink());
            preparedStatement.setInt(11, product.getCategory_FK());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AddProductException();
        }
    }
    @Override
    public void editProduct(Product product) throws EditProductException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update product set label = ?, color = ?, price = ?, cost = ?, size = ?, stock = ?, is_shippable = ?, addition_date = ?,information = ?, image_link = ?, category_id = ? where id = ?;");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getColor());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getCost());
            preparedStatement.setDouble(5, product.getSize());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setBoolean(7, product.getShippable());
            preparedStatement.setDate(8, java.sql.Date.valueOf(product.getAdditionDate()));
            preparedStatement.setString(9, product.getDescription());
            preparedStatement.setString(10, product.getImgLink());
            preparedStatement.setInt(11, product.getCategory_FK());
            preparedStatement.setInt(12, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new EditProductException();
        }
    }
    @Override
    public void deleteProduct(int id) throws DeleteProductException {
        try {
            String sqlInstruction = "delete from product where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteProductException();
        }
    }
    @Override
    public Product getProductById(int id) throws GetProductByIdException {
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
            throw new GetProductByIdException();
        }
        return product;
    }
    @Override
    public ArrayList<Product> getAllProducts() throws GetAllProductsException {
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
            throw new GetAllProductsException();
        }
        return products;
    }
    public ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws GetCustomersWhoPurchasedProductException {
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
            throw new GetCustomersWhoPurchasedProductException();
        }
        return customerByProduct;
    }
    @Override
    public Boolean isArticleAvailableForDeleting(int id) throws IsArticleAvailableForDeletingException {
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
            throw new IsArticleAvailableForDeletingException();
        }
        return isAvailable;
    }

    @Override
    public ArrayList<ProductByFilter> getProductsByFilter(Filter filter) throws GetProductsByFilterException {
        try {
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
                productByFilter = new ProductByFilter(data.getInt("product_id"), data.getString("product_name"), data.getString("category_name"), getLineByProduct(data.getInt("product_id")));
                productsByFilter.add(productByFilter);
            }
            return productsByFilter;
        } catch (SQLException e) {
            throw new GetProductsByFilterException();
        } catch (GetLineByProductException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Line> getLineByProduct(int id) throws GetLineByProductException {
        ArrayList<Line> lines = new ArrayList<>();
        try {
            String sqlInstruction = "select * from line where product_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            Line line;
            while (data.next()) {
                line = new Line(data.getInt("id"), data.getInt("quantity"), data.getDouble("unitary_price"));
                lines.add(line);
            }
        } catch (SQLException e) {
            throw new GetLineByProductException();
        }
        return lines;
    }
}