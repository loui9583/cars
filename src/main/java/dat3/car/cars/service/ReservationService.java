package dat3.car.cars.service;

import dat3.car.cars.dto.MemberResponse;
import dat3.car.cars.dto.ReservationRequestById;
import dat3.car.cars.dto.ReservationResponse;
import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Member;
import dat3.car.cars.entity.Reservation;
import dat3.car.cars.repositories.CarRepository;
import dat3.car.cars.repositories.MemberRepository;
import dat3.car.cars.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    final CarRepository carRepository;
    final MemberRepository memberRepository;

    final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    //Create
    public Reservation createReservation(ReservationRequestById reservationRequestById) {

        Long carId = reservationRequestById.getCarId();
        String memberId = reservationRequestById.getMemberUsername();
        LocalDate startDate = reservationRequestById.getStartDate();
        LocalDate endDate = reservationRequestById.getEndDate();

        Optional<Car> optionalCar = carRepository.findById(carId);
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (startDate.isAfter(endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End-date is before start-date. Please enter a valid interval");
        }

        if (optionalCar.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with ID " + carId + " not found.");
        }

        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with ID " + memberId + " not found.");
        }

        if (startDate.isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't reserve a car before the current date");
        }

        Car car = optionalCar.get();
        Member member = optionalMember.get();

        // Check if a reservation already exists for the same car and overlapping dates
        boolean overlappingReservationExists = reservationRepository.existsByCarAndStartDateBeforeAndEndDateAfter(car, endDate, startDate);

        if (overlappingReservationExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already a reservation for this car on the selected dates.");
        }

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

       return reservationRepository.save(reservation);
    }

    //Read
    public ReservationResponse getReservation(Integer reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation with this id does not exist"));
        return new ReservationResponse(reservation, true, true, true);
    }

    public List<ReservationResponse> getAllReservations(boolean includeDetails){
        return reservationRepository.findAll().stream().map((reservation -> new ReservationResponse(reservation, true, true, includeDetails))).toList();
    }

    public List<ReservationResponse> getReservationsByCarId(Long carId, boolean includeMember){
        return reservationRepository.findByCar_Id(carId).stream().map((reservation -> new ReservationResponse(reservation, true, includeMember, false))).toList();
    }

    public List<ReservationResponse> getReservationsByMemberId(String username){
        return reservationRepository.findByMember_Username(username).stream().map((reservation -> new ReservationResponse(reservation, true, true, false))).toList();
    }

    public void deleteReservation(Integer reservationId){
        reservationRepository.deleteById(reservationId);
    }

    public void updateEndDate(Integer reservationId, LocalDate endDate){
        //TODO
    }

    public List<MemberResponse> findMembersWhoHaveMadeAReservation(){
        //TODO
        return new ArrayList<MemberResponse>();
    }

    public void updateReservationDates(int reservationId, LocalDate newStartDate, LocalDate newEndDate) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservation.setStartDate(newStartDate);
        reservation.setEndDate(newEndDate);
        reservationRepository.save(reservation);
    }


}
