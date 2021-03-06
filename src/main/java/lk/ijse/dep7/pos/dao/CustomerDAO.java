package lk.ijse.dep7.pos.dao;

import lk.ijse.dep7.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveCustomer(Customer customer) throws Exception {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO customer (id,name, address) VALUES (?,?,?)");
        stm.setString(1, customer.getId());
        stm.setString(2, customer.getName());
        stm.setString(3, customer.getAddress());
        stm.executeUpdate();
    }

    public void updateCustomer(Customer customer) throws Exception {
        PreparedStatement stm = connection.prepareStatement("UPDATE customer SET name=?, address=? WHERE id=?");
        stm.setString(1, customer.getName());
        stm.setString(2, customer.getAddress());
        stm.setString(3, customer.getId());
        stm.executeUpdate();
    }

    public void deleteCustomerById(String customerId) throws Exception {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id=?");
        stm.setString(1, customerId);
        stm.executeUpdate();
    }

    public Customer findCustomerById(String customerId) throws Exception {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
        stm.setString(1, customerId);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(customerId, rst.getString("name"), rst.getString("address"));
        } else {
            throw new RuntimeException(customerId + " is not found");
        }

    }

    public List<Customer> findAllCustomer() throws Exception {

        List<Customer> customersList = new ArrayList<>();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM customer");

        while (rst.next()) {
            customersList.add(new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address")));
        }

        return customersList;
    }

    public long countCustomer() throws Exception {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT COUNT(*) FROM customer");
        rst.next();
        return rst.getLong(1);
    }

    public boolean existCustomerById(String customerId) throws Exception {
        PreparedStatement stm = connection.prepareStatement("SELECT id FROM customer WHERE id=?");
        stm.setString(1, customerId);
        return stm.executeQuery().next();
    }

    public List<Customer> findAllCustomers(int page, int size) throws Exception {

        PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer LIMIT ? OFFSET ?;");
        stm.setObject(1, size);
        stm.setObject(2, size * (page - 1));
        ResultSet rst = stm.executeQuery();
        List<Customer> customersList = new ArrayList<>();

        while (rst.next()) {
            customersList.add(new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address")));
        }
        return customersList;
    }

    public String generateNewCustomerId() throws Exception {
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");

        if (rst.next()) {
            return rst.getString("id");
        } else {
            return null;
        }
    }
}

//
//    public  boolean existCustomer(String id) {
//        try (PreparedStatement stm = connection.prepareStatement("SELECT id FROM customer WHERE id=?")) {
//            stm.setString(1, id);
//            return stm.executeQuery().next();
//        } catch (SQLException e) {
//            throw new RuntimeException("Unable to find the customer");
//        }
//    }
//
//    public  long getCustomersCount() {
//
//        try {
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery("SELECT COUNT(*) FROM customer");
//            rst.next();
//            return rst.getLong(1);
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find customer count");
//        }
//    }
//
//    public  void updateCustomer(CustomerDTO customer) {
//
//        try (PreparedStatement stm = connection.prepareStatement("UPDATE customer SET name=?, address=? WHERE id=?")) {
//
//            stm.setString(1, customer.getName());
//            stm.setString(2, customer.getAddress());
//            stm.setString(3, customer.getId());
//            stm.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to Update the customer");
//        }
//
//    }
//
//    public  void deleteCustomer(String id)   {
//        try {
//
//            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id=?");
//            stm.setString(1, id);
//            stm.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to delete the customer " + id, e);
//        }
//    }
//
//    public  CustomerDTO findCustomer(String id)  {
//        try {
//
//            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
//            stm.setString(1, id);
//            ResultSet rst = stm.executeQuery();
//            rst.next();
//            return new CustomerDTO(id, rst.getString("name"), rst.getString("address"));
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find the customer ");
//        }
//    }
//
//    public  List<CustomerDTO> findAllCustomers()  {
//        try {
//            List<CustomerDTO> customersList = new ArrayList<>();
//
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery("SELECT * FROM customer");
//
//            while (rst.next()) {
//                customersList.add(new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address")));
//            }
//
//            return customersList;
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find customers", e);
//        }
//    }
//
//    public  List<CustomerDTO> findAllCustomers(int page, int size)  {
//        try {
//            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer LIMIT ? OFFSET ?;");
//            stm.setObject(1, size);
//            stm.setObject(2, size * (page - 1));
//            ResultSet rst = stm.executeQuery();
//            List<CustomerDTO> customersList = new ArrayList<>();
//
//            while (rst.next()) {
//                customersList.add(new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address")));
//            }
//            return customersList;
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to fetch customers", e);
//        }
//    }
//
//    public   String generateNewCustomerId() throws FailedOperationException {
//        try {
//            ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
//
//            if (rst.next()) {
//                return rst.getString("id");
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            throw new FailedOperationException("Failed to generate a new id", e);
//        }
//    }
//


}
