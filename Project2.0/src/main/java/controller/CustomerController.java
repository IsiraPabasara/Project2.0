package controller;

import Database.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerController {
    private CustomerDAO customerDAO;

    public CustomerController() {
        this.customerDAO = new CustomerDAO();
    }

    public boolean addCustomer(String customerId, String name, String email, String phoneNumber, String address) {
        Customer customer = new Customer(customerId, name, email, phoneNumber, address);
        return customerDAO.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public boolean updateCustomer(String customerId, String name, String email, String phoneNumber, String address) {
        Customer customer = new Customer(customerId, name, email, phoneNumber, address);
        return customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(String customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
}