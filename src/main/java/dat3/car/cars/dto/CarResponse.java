package dat3.car.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    private Long id;
    private String brand;
    private String model;
    private Double pricePrDay;
    private Integer bestDiscount;
    private List<ReservationResponse> reservations;


    LocalDateTime created;
    LocalDateTime edited;

    public CarResponse(Car c, boolean includeAll, boolean includeReservations) {
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        if (includeAll) {
            this.id = c.getId();
            this.created = c.getCreated();
            this.edited = c.getEdited();
            this.bestDiscount = c.getBestDiscount();
        }
        if (includeReservations){
            for (Reservation r : c.getReservations()){
                reservations = new ArrayList<>();
                reservations.add(new ReservationResponse(r,false, true, false));
            }
        }
    }
}
