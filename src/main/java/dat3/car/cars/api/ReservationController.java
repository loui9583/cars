package dat3.car.cars.api;

import dat3.car.cars.dto.ReservationRequestById;
import dat3.car.cars.dto.ReservationResponse;
import dat3.car.cars.entity.Reservation;
import dat3.car.cars.service.ReservationService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
@CrossOrigin
public class ReservationController {

    final
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    Reservation addReservation(@RequestBody ReservationRequestById reservationHelper) {
        return reservationService.createReservation(reservationHelper);
    }

    @PatchMapping("/updateReservationDates/{reservationId}/startDate/{sy}-{sm}-{sd}/endDate/{ey}-{em}-{ed}")
    void updateReservationDates(@PathVariable int reservationId, @PathVariable int sy, @PathVariable int sm, @PathVariable int sd, @PathVariable int ey, @PathVariable int em, @PathVariable int ed) {
        LocalDate newStartDate = LocalDate.of(sy, sm, sd);
        LocalDate newEndDate = LocalDate.of(ey, em, ed);
        reservationService.updateReservationDates(reservationId, newStartDate, newEndDate);
    }

    @GetMapping("/{id}")
    ReservationResponse getReservationById(@PathVariable Integer id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/all")
    List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations(false);
    }

    @GetMapping("/all/includeDetails")
    List<ReservationResponse> getAllReservationsIncludeDetails() {
        return reservationService.getAllReservations(false);
    }

    @GetMapping("/getReservationsByCarId/{carId}")
    List<ReservationResponse> getReservationsByCarId(@PathVariable Long carId) {
        return reservationService.getReservationsByCarId(carId, false);
    }

    @GetMapping("/getReservationsByCarId/{carId}/includeMemberDetails")
    List<ReservationResponse> getReservationsByCarIdIncludeMemberDetails(@PathVariable Long carId) {
        return reservationService.getReservationsByCarId(carId, true);
    }

    @GetMapping("/getReservationsByMemberId/{memberId}")
    List<ReservationResponse> getReservationsByMemberId(@PathVariable String memberId) {
        return reservationService.getReservationsByMemberId(memberId);
    }

    @GetMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
    }
}


