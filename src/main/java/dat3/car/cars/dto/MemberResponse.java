package dat3.car.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.cars.entity.Member;
import dat3.car.cars.entity.Reservation;
import dat3.car.cars.repositories.ReservationRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    String username;
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    LocalDateTime created;
    LocalDateTime edited;
    Integer ranking;
    Boolean approved;
    List<ReservationResponse> reservations;

    public MemberResponse(Member m, Boolean includeAll, boolean includeReservations) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();

        if (includeReservations) {
            this.reservations = new ArrayList<>(); // Initialize the reservations list
            for (Reservation reservation : m.getReservations()){
                reservations.add(new ReservationResponse(reservation,true,false,false));
            }
        }

        if (includeAll) {
            this.created = m.getCreated();
            this.edited = m.getEdited();
            this.approved = m.isApproved();
            this.ranking = m.getRanking();
        }
    }
}


