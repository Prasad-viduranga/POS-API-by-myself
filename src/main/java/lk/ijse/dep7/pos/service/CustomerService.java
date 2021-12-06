package lk.ijse.dep7.pos.service;

import lk.ijse.dep7.pos.dao.CustomerDAO;
import lk.ijse.dep7.pos.dto.CustomerDTO;
import lk.ijse.dep7.pos.exception.DuplicateIdentifierException;
import lk.ijse.dep7.pos.exception.FailedOperationException;
import lk.ijse.dep7.pos.exception.NotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private Connection connection;

    public CustomerService() {
    }

    public CustomerService(Connection connection) {
        this.connection = connection;
    }

    public void saveCustomer(CustomerDTO customer) throws DuplicateIdentifierException, FailedOperationException {
        try {

            if (existCustomer(customer.getId())) {
                throw new DuplicateIdentifierException(customer.getId() + " already exists");
            }

            CustomerDAO.saveCustomer(customer);

        } catch (SQLException e) {
            throw new FailedOperationException("Failed to save the customer", e);
        }
    }

    public long getCustomersCount() throws SQLException {
        return CustomerDAO.getCustomersCount();
    }

    boolean existCustomer(String id) throws SQLException {
        return CustomerDAO.existCustomer(id);
    }

    public void updateCustomer(CustomerDTO customer) throws FailedOperationException, NotFoundException {

        if (!CustomerDAO.existCustomer(customer.getId())) {
            throw new NotFoundException("There is no such customer associated with the id " + customer.getId());
        }
        CustomerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(String id) throws NotFoundException, FailedOperationException {

        if (!CustomerDAO.existCustomer(id)) {
            throw new NotFoundException("There is no such customer associated with the id " + id);
        }
        CustomerDAO.deleteCustomer(id);
    }

    public CustomerDTO findCustomer(String id) throws NotFoundException, FailedOperationException {

        if (!CustomerDAO.existCustomer(id)) {
            throw new NotFoundException("There is no such customer associated with the id " + id);
        }
        return CustomerDAO.findCustomer(id);
    }

    public List<CustomerDTO> findAllCustomers() throws FailedOperationException {
        return CustomerDAO.findAllCustomers();
    }

    public List<CustomerDTO> findAllCustomers(int page, int size) throws FailedOperationException {
        return CustomerDAO.findAllCustomers(page, size);
    }

    public String generateNewCustomerId() throws FailedOperationException {
        return CustomerDAO.generateNewCustomerId();
    }

}
