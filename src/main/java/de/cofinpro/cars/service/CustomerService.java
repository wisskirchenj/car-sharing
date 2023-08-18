package de.cofinpro.cars.service;

import de.cofinpro.cars.persistence.Car;
import de.cofinpro.cars.persistence.Customer;
import de.cofinpro.cars.persistence.CustomerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return repository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public void createCustomer(String name) {
        repository.save(new Customer().setName(name));
    }

    public void rentCar(Customer customer, Car car) {
        customer.setRentedCar(car);
        repository.save(customer);
    }

    public void returnCar(Customer customer) {
        rentCar(customer, null);
    }
}
