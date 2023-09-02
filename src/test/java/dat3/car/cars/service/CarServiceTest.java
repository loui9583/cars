package dat3.car.cars.service;

import dat3.car.cars.dto.CarRequest;
import dat3.car.cars.dto.CarResponse;
import dat3.car.cars.entity.Car;
import dat3.car.cars.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*Da jeg brugte @DataJpaTest passede mine tests individuelt, men ikke når jeg kørte dem samlet.
Da jeg brugte @SpringBootTest sammen med @Transactional virkede det.
Er godt klar over det ikke er det her der er meningen, men jeg synes det var nogle okay test så jeg ville gerne
have dem med og kunne kun få det til at virke sådan her. Testede også api'erne manuelt med postman
og der virkede det også.
*/
@Transactional
@SpringBootTest
class CarServiceTest {

    @Autowired
    CarRepository carRepository;

    CarService carService;

    Car c1, c2;

    @BeforeEach
    void setUp() {
        c1 = carRepository.save(new Car("brand1", "model1", 100.5, 15));
        c2 = carRepository.save(new Car("brand2", "model2", 200.5, 25));
        carService = new CarService(carRepository);
    }

    @Test
    public void shouldAddNewCar() {
        Car car = new Car("brand3","model3",300.0,125);
        CarRequest carRequest = new CarRequest(car);
        long count1 = carRepository.count();
        carService.addCar(carRequest);
        long count2 = carRepository.count();
        assertTrue(count2>count1);
    }

    @Test
    public void shouldEditCarById() {
        // Test redigering af en eksisterende bil ved hjælp af dens ID og kontroller, om ændringer blev gemt korrekt.
        Car car = new Car("test","test1",1,2);
        CarRequest carRequest = new CarRequest(car);
        carService.editCarById(carRequest,1L);
        assertEquals("test1",carService.getCarById(1L).getModel());
        assertEquals("test",carService.getCarById(1L).getBrand());
        assertEquals(1,carService.getCarById(1L).getPricePrDay());
        assertEquals(2,carService.getCarById(1L).getBestDiscount());
    }

    @Test
    public void shouldGetCarByIdIncludeAllFalse() {
        CarResponse carResponse = new CarResponse(carService.getCarById(1L),false);

        assertNotNull(carResponse.getModel());
        assertNotNull(carResponse.getBrand());
        assertNotNull(carResponse.getPricePrDay());
        //###
        assertNull(carResponse.getBestDiscount());
        assertNull(carResponse.getCreated());
        assertNull(carResponse.getEdited());
    }

    @Test
    public void shouldGetAllCarsIncludeAllTrue() {
        // Test at hente alle biler med includeAll = true og kontroller, at ingen felter er null.
        List<CarResponse> getAllCars = carService.getCars(true);
        for (CarResponse carResponse : getAllCars){
            assertNotNull(carResponse.getModel());
            assertNotNull(carResponse.getBrand());
            assertNotNull(carResponse.getPricePrDay());
            //###
            assertNotNull(carResponse.getBestDiscount());
            assertNotNull(carResponse.getCreated());
            assertNotNull(carResponse.getEdited());
        }
    }

    @Test
    public void shouldGetAllCarsIncludeAllFalse() {
        // Test at hente alle biler med includeAll = false og kontroller, at ekstra felter er null.
        List<CarResponse> getAllCars = carService.getCars(false);
        for (CarResponse carResponse : getAllCars){
            assertNotNull(carResponse.getModel());
            assertNotNull(carResponse.getBrand());
            assertNotNull(carResponse.getPricePrDay());
            //###
            assertNull(carResponse.getBestDiscount());
            assertNull(carResponse.getCreated());
            assertNull(carResponse.getEdited());
        }
    }

    @Test
    public void shouldGetCarByIdIncludeAllTrue() {
        CarResponse carResponse = new CarResponse(carService.getCarById(1L),true);

        assertNotNull(carResponse.getModel());
        assertNotNull(carResponse.getBrand());
        assertNotNull(carResponse.getPricePrDay());
        //###
        assertNotNull(carResponse.getBestDiscount());
        assertNotNull(carResponse.getCreated());
        assertNotNull(carResponse.getEdited());
    }

    @Test
    public void shouldSetPricePrDayById() {
        // Test indstilling af daglig pris for en eksisterende bil ved hjælp af dens ID og kontroller, om prisen blev opdateret korrekt.
        carService.setPricePrDayById(1L, 10.0);
        assertEquals(10.0, carService.getCarById(1L).getPricePrDay());
    }

    @Test
    public void shouldSetBestDiscountById() {
        // Test indstilling af den bedste rabat for en eksisterende bil ved hjælp af dens ID og kontroller, om rabatten blev opdateret korrekt.
        carService.setBestDiscountById(1L, 10);
        assertEquals(10,carService.getCarById(1L).getBestDiscount());
    }

    @Test
    public void shouldNotAddDuplicateCar() {
        // Test at forsøge at tilføje en bil med samme model og mærke som en eksisterende bil.
        // Forvent en undtagelse (exception) at blive kastet.
        Car car = new Car("brand1","model1",300.0,125);
        CarRequest carRequest = new CarRequest(car);
        assertThrows(ResponseStatusException.class, () -> carService.addCar(carRequest));
    }

}