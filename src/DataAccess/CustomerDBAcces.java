package DataAccess;

import Exceptions.ConnectionException;
import Exceptions.GetAllCustomersException;
import Exceptions.GetBoughtHistoryException;
import Exceptions.GetCustomerByIdException;
import Model.Customer;
import Model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDBAcces implements CustomerDAO {

    private Connection connection;
    public CustomerDBAcces() throws ConnectionException {
        connection = SingletonConnexion.getInstance();
    }

    public Customer getCustomerById(int id) throws GetCustomerByIdException {
        Customer customer = null;

        try {
            String sqlInstruction = "select * from customer where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"), data.getString("email"), data.getInt("locality_id"), data.getString("phone"), data.getString("street_and_number"));
            }
        } catch (SQLException e) {
            throw new GetCustomerByIdException();
        }

        return customer;
    }

    public ArrayList<Customer> getAllCustomers() throws GetAllCustomersException {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            String sqlInstruction = "select * from customer;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Customer customer;

            while (data.next()) {
                customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"), data.getString("email"), data.getInt("locality_id"), data.getString("phone"), data.getString("street_and_number"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new GetAllCustomersException();
        }

        return customers;
    }

    @Override
    public ArrayList<Purchase> getBoughtHistory(int id) throws GetBoughtHistoryException {
        ArrayList<Purchase> purchaseList = new ArrayList<>();

        try {
            String sqlInstruction = "select c.label, p.label, l.unitary_price, l.quantity, o.id from product p inner join `category` c on c.id = p.category_id inner join `line` l on p.id = l.product_id inner join `order` o on l.order_id = o.id inner join customer on o.customer_id = customer.id where customer.id = (?) order by o.id desc";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);

            ResultSet data = preparedStatement.executeQuery();

            Purchase purchase;

            while (data.next()) {
                purchase = new Purchase(data.getInt("o.id"), data.getInt("l.quantity"), data.getDouble("l.unitary_price"), data.getString("p.label"), data.getString("c.label"));
                purchaseList.add(purchase);
            }
        } catch (SQLException e) {
            throw new GetBoughtHistoryException();
        }

        return purchaseList;
    }
}
