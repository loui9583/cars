package dat3.car.cars.repositories;

import dat3.car.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);

    List<Car> findByModel(String model);

    @Query("SELECT c FROM Car c WHERE c.brand = :brand AND c.model = :model")
    List<Car> findByBrandAndModel(@Param("brand") String brand, @Param("model") String model);

    List<Car> findByOrderByBestDiscountDesc();

    boolean existsByBrandAndModelIgnoreCase(String brand, String model);

    List<Car> findByReservationsIsNull();

    List<Car> findByReservationsIsNotNull();

    @Query("SELECT AVG(c.pricePrDay) FROM Car c")
    Double calculateAveragePricePerDay();
}

