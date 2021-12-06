package lk.ijse.dep7.pos.service;

import lk.ijse.dep7.pos.dao.CustomerDAO;
import lk.ijse.dep7.pos.dto.CustomerDTO;
import lk.ijse.dep7.pos.exception.DuplicateIdentifierException;
import lk.ijse.dep7.pos.exception.FailedOperationException;
import lk.ijse.dep7.pos.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private Connection connection;
    private CustomerDAO customerDAO;

    public CustomerService() {

    }

    public CustomerService(Connection connection) {
        this.connection = connection;
        this.customerDAO = new CustomerDAO(connection);

    }

    public void saveCustomer(CustomerDTO customer) throws DuplicateIdentifierException, FailedOperationException {
        try {

            if (existCustomer(customer.getId())) {
                throw new DuplicateIdentifierException(customer.getId() + " already exists");
            }

            customerDAO.saveCustomer(customer);

        } catch (SQLException e) {
            throw new FailedOperationException("Failed to save the customer", e);
        }
    }

    public long getCustomersCount() throws SQLException {
        return customerDAO.getCustomersCount();
    }

    boolean existCustomer(String id) throws SQLException {
        return customerDAO.existCustomer(id);
    }

    public void updateCustomer(CustomerDTO customer) throws FailedOperationException, NotFoundException {

        if (!customerDAO.existCustomer(customer.getId())) {
            throw new NotFoundException("There is no such customer associated with the id " + customer.getId());
        }
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(String id) throws NotFoundException, FailedOperationException {

        if (!customerDAO.existCustomer(id)) {
            throw new NotFoundException("There is no such customer associated with the id " + id);
        }
        customerDAO.deleteCustomer(id);
    }

    public CustomerDTO findCustomer(String id) throws NotFoundException, FailedOperationException {

        if (!customerDAO.existCustomer(id)) {
            throw new NotFoundException("There is no such customer associated with the id " + id);
        }
        return customerDAO.findCustomer(id);
    }

    public List<CustomerDTO> findAllCustomers() throws FailedOperationException {
        return customerDAO.findAllCustomers();
    }

    public List<CustomerDTO> findAllCustomers(int page, int size) throws FailedOperationException {
        return customerDAO.findAllCustomers(page, size);
    }

    public  String generateNewCustomerId() throws FailedOperationException {
        String id = customerDAO.generateNewCustomerId();
        return id != null ? customerDAO.generateNewCustomerId() : "C001";
    }

}
