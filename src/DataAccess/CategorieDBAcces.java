package DataAccess;

import Exceptions.ConnectionException;
import Exceptions.GetAllCategoriesException;
import Exceptions.GetCategoryByIdException;
import Exceptions.GetSuppliersByCategoryException;
import Model.Category;
import Model.SupplierByCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDBAcces implements CategorieDAO {

    private Connection connection;
    public CategorieDBAcces() throws ConnectionException {
        connection = SingletonConnexion.getInstance();
    }

    @Override
    public Category getCategoryById(int id) throws GetCategoryByIdException {
        Category category = null;
        try {
            String sqlInstruction = "select * from category where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                category = new Category();
                category.setId(data.getInt("id"));
                category.setLabel(data.getString("label"));
            }
        } catch (SQLException exception) {
            throw new GetCategoryByIdException();
        }
        return category;
    }

    public ArrayList<Category> getAllCategories() throws GetAllCategoriesException {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sqlInstruction = "select * from category;";
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
            throw new GetAllCategoriesException();
        }
        return categories;
    }

    @Override
    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws GetSuppliersByCategoryException {
        ArrayList<SupplierByCategory> suppliersByCategory = new ArrayList<>();

        try {
            String sqlInstruction = "SELECT DISTINCT  c.label, l.label, s.label from `supplier` s INNER JOIN `supply` on s.id = supply.supplier_id INNER JOIN `product` p on supply.product_id = p.id INNER JOIN `category` on p.category_id = category.id INNER JOIN `locality` l on s.locality_id = l.id INNER JOIN `country` c on l.country_id = c.id WHERE category.id = (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);

            ResultSet data = preparedStatement.executeQuery();

            SupplierByCategory supplierByCategory;

            while (data.next()) {
                supplierByCategory = new SupplierByCategory(data.getString("c.label"), data.getString("l.label"), data.getString("s.label"));
                suppliersByCategory.add(supplierByCategory);
            }
        } catch (SQLException e) {
            throw new GetSuppliersByCategoryException();
        }

        return suppliersByCategory;
    }


}
