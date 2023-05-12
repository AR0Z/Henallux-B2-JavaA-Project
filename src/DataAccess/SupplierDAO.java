package DataAccess;

import Exceptions.DBExceptions;
import Model.Supplier;

import java.util.ArrayList;


public interface SupplierDAO {
    ArrayList<Supplier> getAllSupplier() throws DBExceptions;

}
