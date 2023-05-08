package DataAccess;

import Exceptions.DBExceptions;
import Model.Category;
import Model.Purchase;
import Model.SupplierByCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDBAcces implements CategorieDAO{

    @Override
    public Category getCategoryById(int id) throws DBExceptions {
        Category category = null;
        try {
            String sqlInstruction = "select * from category where id = ?;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                category = new Category();
                category.setId(data.getInt("id"));
                category.setLabel(data.getString("label"));
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return category;
    }

    public ArrayList<Category> getAllCategories() throws DBExceptions {
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
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return categories;
    }

    @Override
    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws DBExceptions {
        ArrayList<SupplierByCategory> suppliersByCategory = new ArrayList<>();

        try {
            String sqlInstruction = "SELECT DISTINCT  c.label, l.label, s.label from `supplier` s INNER JOIN `supply` on s.id = supply.supplier_id INNER JOIN `product` p on supply.product_id = p.id INNER JOIN `category` on p.category_id = category.id INNER JOIN `locality` l on s.locality_id = l.id INNER JOIN `country` c on l.country_id = c.id WHERE category.id = (?);";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);

            ResultSet data = preparedStatement.executeQuery();

            SupplierByCategory supplierByCategory;

            while(data.next()){
                supplierByCategory = new SupplierByCategory(data.getString("c.label"), data.getString("l.label"), data.getString("s.label"));
                suppliersByCategory.add(supplierByCategory);
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }

        return suppliersByCategory;
    }


}
