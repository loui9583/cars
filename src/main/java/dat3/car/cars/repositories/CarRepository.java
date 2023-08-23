package dat3.car.cars.repositories;

import dat3.car.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);

}
