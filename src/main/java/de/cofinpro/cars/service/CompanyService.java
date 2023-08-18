package de.cofinpro.cars.service;

import de.cofinpro.cars.persistence.Company;
import de.cofinpro.cars.persistence.CompanyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getCompanies() {
        return repository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public void createCompany(String name) {
        repository.save(new Company().setName(name));
    }
}
