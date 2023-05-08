package DataAccess;

import Exceptions.DBExceptions;
import Model.Customer;
import Model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDBAcces implements CustomerDAO {

    public Customer getCustomerById(int id) throws DBExceptions {
        Customer customer = null;

        try {
            String sqlInstruction = "select * from customer where id = ?;";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();

            if(data.next()){
                customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"), data.getString("email"), data.getInt("locality_id"), data.getString("phone"), data.getString("street_and_number"));
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }

        return customer;
    }

    public ArrayList<Customer> getAllCustomers() throws DBExceptions {
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
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }

        return customers;
    }

    @Override
    public ArrayList<Purchase> getBoughtHistory(int id) throws DBExceptions {
        ArrayList<Purchase> purchaseList = new ArrayList<>();

        try {
            String sqlInstruction = "select c.label, p.label, l.unitary_price, l.quantity, o.id from product p inner join `category` c on c.id = p.category_id inner join `line` l on p.id = l.product_id inner join `order` o on l.order_id = o.id inner join customer on o.customer_id = customer.id where customer.id = (?) order by o.id desc";
            Connection connection = SingletonConnexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);

            ResultSet data = preparedStatement.executeQuery();

            Purchase purchase;

            while(data.next()){
                purchase = new Purchase(data.getInt("o.id"), data.getInt("l.quantity"), data.getDouble("l.unitary_price"), data.getString("p.label"), data.getString("c.label"));
                purchaseList.add(purchase);
            }
        } catch (SQLException e) {
            throw new DBExceptions(e.getMessage());
        }

        return purchaseList;
    }
}
