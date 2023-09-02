package dat3.car.cars.service;

import dat3.car.cars.dto.CarRequest;
import dat3.car.cars.dto.CarResponse;
import dat3.car.cars.entity.Car;
import dat3.car.cars.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                ((Car -> new CarResponse(Car, includeAll))).toList();
    }
    public CarResponse addCar(CarRequest body) {
        Car newCar = CarRequest.getCarEntity(body);
        if (carRepository.existsByBrandAndModelIgnoreCase(body.getBrand(), body.getModel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This car model already exists.");
        }
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, true);
    }
    //########
    public Car getCarById(Long id){
        return carRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Car with this id does not exist"));
    }

    public CarResponse findById(Long id, boolean includeAll){
        return new CarResponse(getCarById(id),true);
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
    //########
}
