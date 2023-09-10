package dat3.car.cars.service;

import dat3.car.cars.dto.CarRequest;
import dat3.car.cars.dto.CarResponse;
import dat3.car.cars.entity.Car;
import dat3.car.cars.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    final
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        return carRepository.findAll().stream().map
                ((Car -> new CarResponse(Car, includeAll, false))).toList();
    }

    public CarResponse addCar(CarRequest body) {
        Car newCar = CarRequest.getCarEntity(body);
        if (carRepository.existsByBrandAndModelIgnoreCase(body.getBrand(), body.getModel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This car model already exists.");
        }
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, true, false);
    }

    //########
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Car with this id does not exist"));
    }

    public CarResponse findById(Long id, boolean includeAll) {
        return new CarResponse(getCarById(id), true, false);
    }

    public Car editCarById(CarRequest body, Long id) {
        Car car = getCarById(id);
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setBestDiscount(body.getBestDiscount());
        car.setPricePrDay(body.getPricePrDay());
        car = carRepository.save(car);
        return car;
    }

    public Car setPricePrDayById(Long id, Double pricePrDay) {
        Car car = getCarById(id);
        car.setPricePrDay(pricePrDay);
        car = carRepository.save(car);
        return car;
    }

    public Car setBestDiscountById(Long id, Integer bestDiscount) {
        Car car = getCarById(id);
        car.setBestDiscount(bestDiscount);
        car = carRepository.save(car);
        return car;
    }

    public List<CarResponse> findCarsByBrandAndModel(String brand, String model) {

        List<CarResponse> cars = new ArrayList<>();
        for (Car car : carRepository.findByBrandAndModel(brand, model)){
            CarResponse carResponse = new CarResponse(car, false, false);
            cars.add(carResponse);
        }
        return cars;
    }

    public List<CarResponse> getAllCarsSortByBestDiscount() {

        List<CarResponse> cars = new ArrayList<>();
        for (Car car : carRepository.findByOrderByBestDiscountDesc()){
            CarResponse carResponse = new CarResponse(car, true, false);
            cars.add(carResponse);
        }
        return cars;
    }

    public List<CarResponse> getAllCarsWhereReservationsIsNull() {

        List<CarResponse> cars = new ArrayList<>();
        for (Car car : carRepository.findByReservationsIsNull()){
            CarResponse carResponse = new CarResponse(car, true, true);
            cars.add(carResponse);
        }
        return cars;
    }

    public Double avgPricePrDay(){
        return carRepository.calculateAveragePricePerDay();
    }

    public List<CarResponse> getAllCarsWhereReservationsIsNotNull() {

        List<CarResponse> cars = new ArrayList<>();
        for (Car car : carRepository.findByReservationsIsNotNull()){
            CarResponse carResponse = new CarResponse(car, true, true);
            cars.add(carResponse);
        }
        return cars;
    }



    public List<Car> findCarsThatHasNoReservation() {
        //TODO
        return new ArrayList<Car>();
    }

    public List<Car> findCarsSortByDiscount() {
        //TODO
        return new ArrayList<Car>();
    }

    public double averagePriceForAllCars() {
        //TODO
        return 0.0;
    }


    //########
}
