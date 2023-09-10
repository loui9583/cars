package dat3.car.cars.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Reservation extends AdminDetails {


    public Reservation(LocalDate startDate, LocalDate endDate, Member member, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.car = car;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne
    Member member;

    @ManyToOne
    Car car;
}
