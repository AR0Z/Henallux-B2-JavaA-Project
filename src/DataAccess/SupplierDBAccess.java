package DataAccess;

import Exceptions.DBExceptions;
import Model.Supplier;

import java.sql.*;
import java.util.ArrayList;


public class SupplierDBAccess implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAllSuppliers() throws DBExceptions {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try {
            String sqlInstruction = "select * from supplier;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Supplier supplier;
            while (data.next()) {
                supplier = new Supplier(data.getInt("id"), data.getString("label"), data.getString("phone"), data.getString("email"), data.getString("street_and_number"), data.getInt("locality_id"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }
        return suppliers;
    }
}