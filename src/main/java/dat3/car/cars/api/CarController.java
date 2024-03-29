package dat3.car.cars.api;

import dat3.car.cars.dto.CarRequest;
import dat3.car.cars.dto.CarResponse;
import dat3.car.cars.entity.Car;
import dat3.car.cars.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@CrossOrigin
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars() {
        return carService.getCars(false);
    }

    @GetMapping("/includeAll")
    public List<CarResponse> getCarsIncludeAll() {
        return carService.getCars(true);
    }

    @GetMapping("/id/{id}")
    CarResponse carResponseById(@PathVariable Long id) {
        return new CarResponse(carService.getCarById(id), false, false);
    }

    @GetMapping("/id/{id}/includeAll")
    CarResponse carResponseByIdIncludeAll(@PathVariable Long id) {
        return new CarResponse(carService.getCarById(id), true, false);
    }

    @GetMapping("/findByBrandAndModel/{brand}/{model}")
    List<CarResponse> carResponses(@PathVariable String brand, @PathVariable String model) {
        return carService.findCarsByBrandAndModel(brand, model);
    }

    @GetMapping("/orderByBestDiscount")
    List<CarResponse> orderByBestDiscount() {
        return carService.getAllCarsSortByBestDiscount();
    }

    @GetMapping("/findAllWhereReservationIsNotNull")
    List<CarResponse> findAllWhereReservationIsNotNull() {
        return carService.getAllCarsWhereReservationsIsNotNull();
    }

    @GetMapping("/findAllWhereReservationIsNull")
    List<CarResponse> findAllWhereReservationIsNull() {
        return carService.getAllCarsWhereReservationsIsNull();
    }

    @PostMapping()
    CarResponse addMember(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    @PutMapping("/id/{id}")
    Car editCarById(@RequestBody CarRequest body, @PathVariable Long id) {
        return carService.editCarById(body, id);
    }

    @PatchMapping("/id/pricePrDay/{id}/{pricePrDay}")
    Car setPricePrDay(@PathVariable Long id, @PathVariable Double pricePrDay) {
        return carService.setPricePrDayById(id, pricePrDay);
    }

    @PatchMapping("/id/bestDiscount/{id}/{bestDiscount}")
    Car setBestDiscount(@PathVariable Long id, @PathVariable Integer bestDiscount) {
        return carService.setBestDiscountById(id, bestDiscount);
    }

    @GetMapping("/avgPricePrDay")
    Double avgPricePrDay() {
        return carService.avgPricePrDay();
    }

    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable long id){
        carService.deleteCar(id);
    }
}