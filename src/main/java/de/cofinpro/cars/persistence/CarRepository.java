package de.cofinpro.cars.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByCompanyId(int id, Sort sort);

    @Query(value = "SELECT car FROM Car car "
                   + "LEFT JOIN Customer cust on cust.rentedCar.id = car.id "
                   + "WHERE cust IS NULL AND car.company.id = :companyId")
    List<Car> findAvailableByCompanyId(@Param("companyId") int companyId, Sort sort);
}
