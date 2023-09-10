package dat3.car.cars.service;

import dat3.car.cars.dto.ReservationRequestById;
import dat3.car.cars.dto.ReservationResponse;
import dat3.car.cars.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @Autowired
    CarService carService;

    @Test
    void createReservation() {
        int initialSize = reservationService.getAllReservations(false).size();
        LocalDate startDate = LocalDate.of(2025,10,10);
        LocalDate endDate = LocalDate.of(2026,10,10);
        String memberUsername = "john_doe";
        Long carId = 11L;
        reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId));
        boolean isSizedIncreased = reservationService.getAllReservations(false).size()>initialSize;
        assertTrue(isSizedIncreased);
    }

    @Test
    void createReservationEndDateIsBeforeStartDateThrows() {

        LocalDate startDate = LocalDate.of(2025,10,10);
        LocalDate endDate = LocalDate.of(2024,10,10);
        String memberUsername = "john_doe";
        Long carId = 11L;
        //reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId)));
        assertEquals("400 BAD_REQUEST \"End-date is before start-date. Please enter a valid interval\"", exception.getMessage());

    }

    @Test
    void createReservationMemberNotFoundThrows() {

        LocalDate startDate = LocalDate.of(2024,10,10);
        LocalDate endDate = LocalDate.of(2025,10,10);
        String memberUsername = "spaghetti";
        Long carId = 11L;
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId)));
        assertEquals("400 BAD_REQUEST \"Member with ID spaghetti not found.\"", exception.getMessage());

    }


    @Test
    void createReservationCarNotFoundThrows() {
        LocalDate startDate = LocalDate.of(2024,10,10);
        LocalDate endDate = LocalDate.of(2025,10,10);
        String memberUsername = "john_doe";
        Long carId = 99999999L;
        //reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId)));
        assertEquals("400 BAD_REQUEST \"Car with ID 99999999 not found.\"", exception.getMessage());
    }

    @Test
    void createReservationCarIsAlreadyReservedThrows() {

        ReservationResponse r = reservationService.getReservation(1);

        LocalDate startDate = LocalDate.of(2026,10,15);
        LocalDate endDate = LocalDate.of(2027,10,15);
        String memberUsername = "john_doe";
        Long carId = 1L;
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.createReservation(new ReservationRequestById(startDate,endDate,memberUsername,carId)));
        assertEquals("400 BAD_REQUEST \"There is already a reservation for this car on the selected dates.\"", exception.getMessage());

    }



    @Test
    void getReservation() {

    }

    @Test
    void getAllReservations() {
    }

    @Test
    void getReservationsByCarId() {
    }

    @Test
    void getReservationsByMemberId() {
    }

    @Test
    void deleteReservation() {
    }

    @Test
    void updateEndDate() {
    }

    @Test
    void findMembersWhoHaveMadeAReservation() {
    }

    @Test
    void updateReservationDates() {
    }
}