package dat3.car.cars.repositories;

import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {


}
