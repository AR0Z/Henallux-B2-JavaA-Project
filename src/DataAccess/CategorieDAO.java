package DataAccess;

import Exceptions.DBExceptions;
import Model.Category;

import java.util.ArrayList;

public interface CategorieDAO {
    Category getCategoryById(int id) throws DBExceptions;
    ArrayList<Category> getAllCategories() throws DBExceptions;
}
