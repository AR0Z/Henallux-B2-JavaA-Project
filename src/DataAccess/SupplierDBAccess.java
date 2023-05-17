package DataAccess;

import Exceptions.DBExceptions;
import Exceptions.GetAllSuppliersException;
import Model.Supplier;

import java.sql.*;
import java.util.ArrayList;


public class SupplierDBAccess implements SupplierDAO {

    private Connection connection;
    public SupplierDBAccess() {
        try{
            connection = SingletonConnexion.getInstance();
        }catch (DBExceptions exceptions) {
            exceptions.printStackTrace();
        }

    }
    @Override
    public ArrayList<Supplier> getAllSuppliers() throws GetAllSuppliersException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try {
            String sqlInstruction = "select * from supplier;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Supplier supplier;
            while (data.next()) {
                supplier = new Supplier(data.getInt("id"), data.getString("label"), data.getString("phone"), data.getString("email"), data.getString("street_and_number"), data.getInt("locality_id"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            throw new GetAllSuppliersException();
        }
        return suppliers;
    }
}