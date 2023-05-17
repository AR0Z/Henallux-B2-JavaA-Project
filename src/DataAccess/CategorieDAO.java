package DataAccess;

import Exceptions.GetAllCategoriesException;
import Exceptions.GetCategoryByIdException;
import Exceptions.GetSuppliersByCategoryException;
import Model.Category;
import Model.SupplierByCategory;

import java.util.ArrayList;

public interface CategorieDAO {
    Category getCategoryById(int id) throws GetCategoryByIdException;

    ArrayList<Category> getAllCategories() throws GetAllCategoriesException;

    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws GetSuppliersByCategoryException;
}
