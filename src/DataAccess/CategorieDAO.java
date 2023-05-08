package DataAccess;

import Exceptions.DBExceptions;
import Model.Category;
import Model.SupplierByCategory;

import java.util.ArrayList;

public interface CategorieDAO {
    Category getCategoryById(int id) throws DBExceptions;
    ArrayList<Category> getAllCategories() throws DBExceptions;

    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws DBExceptions;
}
