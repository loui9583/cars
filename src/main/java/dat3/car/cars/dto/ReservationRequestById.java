package dat3.car.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReservationRequestById {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    String memberUsername;
    Long carId;

    public static ReservationRequestById getReservationHelper(ReservationRequestById r) {
        return new ReservationRequestById(r.startDate, r.endDate, r.memberUsername, r.carId);
    }

}
