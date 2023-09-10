package dat3.car.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Member;
import dat3.car.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    Member member;
    Car car;

    public static Reservation getReservationEntity(ReservationRequest r) {
        return new Reservation(r.startDate, r.endDate, r.member, r.car);
    }
    public ReservationRequest(Reservation r){
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.member = r.getMember();
        this.car = r.getCar();
    }
}
