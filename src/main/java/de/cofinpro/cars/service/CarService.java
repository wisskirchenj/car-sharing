package de.cofinpro.cars.service;

import de.cofinpro.cars.persistence.Car;
import de.cofinpro.cars.persistence.CarRepository;
import de.cofinpro.cars.persistence.Company;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository carRepository) {
        this.repository = carRepository;
    }

    public List<Car> getCars(int companyId) {
        return repository.findAllByCompanyId(companyId, Sort.by(Sort.Order.asc("id")));
    }

    public List<Car> getAvailableCars(int companyId) {
        return repository.findAvailableByCompanyId(companyId, Sort.by(Sort.Order.asc("id")));
    }

    public void createCar(String name, Company company) {
        repository.save(new Car().setName(name).setCompany(company));
    }
}
