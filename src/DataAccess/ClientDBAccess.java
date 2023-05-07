package DataAccess;

import Exceptions.DBExceptions;
import Exceptions.getAllCustomersException;
import Model.Category;
import Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDBAccess {

    public ArrayList<Customer> getAllCustomers() throws getAllCustomersException {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            String sqlInstruction = "select * from customer;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Customer customer;

            while(data.next()){
                customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"), data.getString("email"), data.getInt("locality_id"), data.getString("phone"), data.getString("street_and_number"));
                customers.add(customer);
            }
        } catch (SQLException | DBExceptions e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

}
