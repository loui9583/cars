package dat3.car.cars.repositories;

import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

List<Member> findAllByReservationsIsNotNull();

}
