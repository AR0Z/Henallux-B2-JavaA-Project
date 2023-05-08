package DataAccess;

import Exceptions.DBExceptions;
import Model.Category;

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
}
