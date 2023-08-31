package dat3.car.cars.config;

import dat3.car.cars.entity.Car;

import java.util.*;

public class CarTestDataFactory {

    private static final List<String> brands = Arrays.asList(
            "Toyota", "Honda", "Ford", "BMW", "Mercedes", "Chevrolet", "Volkswagen", "Audi", "Hyundai", "Nissan"
    );
    private static final Map<String, List<String>> models = Map.of(
            "Toyota", Arrays.asList("Camry", "Corolla", "Prius"),
            "Honda", Arrays.asList("Civic", "Accord", "CR-V"),
            "Ford", Arrays.asList("Focus", "Mustang", "Fiesta"),
            "BMW", Arrays.asList("M3", "X3", "X5"),
            "Mercedes", Arrays.asList("C-Class", "E-Class", "A-Class"),
            "Chevrolet", Arrays.asList("Malibu", "Impala", "Camaro"),
            "Volkswagen", Arrays.asList("Golf", "Passat", "Tiguan"),
            "Audi", Arrays.asList("A4", "A6", "Q5"),
            "Hyundai", Arrays.asList("Elantra", "Santa Fe", "Tucson"),
            "Nissan", Arrays.asList("Altima", "Maxima", "Rogue")
    );

    private static final List<Integer> discounts = Arrays.asList(2,5,10,15,20,25,30,35,40,45);

    public static List<Car> generateTestCars() {
        Random random = new Random();
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String brand = brands.get(random.nextInt(brands.size()));
            String model = models.get(brand).get(random.nextInt(models.get(brand).size()));
            double price = 200 + random.nextInt(800);
            Integer discount = discounts.get(random.nextInt(discounts.size()));
            Car car = new Car(brand, model, price, discount);
            cars.add(car);
        }
        return cars;
    }
}