    package dat3.car.cars.repositories;

    import dat3.car.cars.entity.Car;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

    import java.util.List;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertTrue;


    @DataJpaTest
    class CarRepositoryTest {

        @Autowired
        CarRepository carRepository;

        @Test
        public void testFindAll(){
            Car car = new Car("Toyota","Camry",100,100);
            carRepository.save(car);
            assertEquals(1,carRepository.findAll().size());

        }

        @Test
        public void testAddRowToDb(){
            int startValue = carRepository.findAll().size();
            Car car = new Car("BMW","M3",100,10);
            carRepository.save(car);
            int endValue = carRepository.findAll().size();
            boolean isEndValueBiggerThanStartValue = endValue > startValue;
            assertTrue(isEndValueBiggerThanStartValue);
        }


        @Test
        public void testFindByBrand() {
            // Create and save sample cars
            Car car1 = new Car("Toyota", "Corolla", 120, 15);
            Car car2 = new Car("Toyota", "Camry", 150, 10);
            carRepository.save(car1);
            carRepository.save(car2);

            List<Car> toyotaCars = carRepository.findByBrand("Toyota");
            assertEquals(2, toyotaCars.size());
        }

        @Test
        public void testFindByModel() {
            // Create and save sample cars
            Car car1 = new Car("Toyota", "Camry", 150, 10);
            Car car2 = new Car("BMW", "M3", 200, 20);
            carRepository.save(car1);
            carRepository.save(car2);

            List<Car> camryCars = carRepository.findByModel("Camry");
            assertEquals(1, camryCars.size());
        }
    }

