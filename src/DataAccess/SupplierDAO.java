package DataAccess;

import Exceptions.GetAllSuppliersException;
import Model.Supplier;

import java.util.ArrayList;


public interface SupplierDAO {
    ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException;

}
