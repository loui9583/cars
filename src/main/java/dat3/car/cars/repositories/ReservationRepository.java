package dat3.car.cars.repositories;

import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    boolean existsByCarAndStartDateBeforeAndEndDateAfter(Car car, LocalDate startDate,LocalDate endDate);
    boolean existsByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate);
    List<Reservation> findByCar_Id(Long car_id);
    List<Reservation> findByMember_Username(String username);


}
