package dat3.car.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.cars.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    Integer id;
    LocalDate startDate;
    LocalDate endDate;
    MemberResponse member;
    CarResponse car;
    LocalDateTime created;
    LocalDateTime edited;

    public ReservationResponse(Reservation r, boolean includeCar, boolean includeMember, boolean includeAdminDetails) {

        this.id = r.getId();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();

        if (includeMember) {
            this.member = new MemberResponse(r.getMember(), false, false);
        }

        if (includeCar) {
            this.car = new CarResponse(r.getCar(),false, false);
        }

        if (includeAdminDetails) {
            this.edited = r.getEdited();
            this.created = r.getCreated();
        }
    }
}
