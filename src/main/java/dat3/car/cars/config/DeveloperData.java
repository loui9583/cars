package dat3.car.cars.config;

import dat3.car.cars.entity.Car;
import dat3.car.cars.entity.Member;
import dat3.car.cars.repositories.CarRepository;
import dat3.car.cars.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        carRepository.saveAll(generateCars());
        memberRepository.saveAll(generateMembers());
    }

    @GetMapping("/members")
     List<Member> generateMembers() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("johndoe", "password123", "john@example.com", "John", "Doe", "123 Main St", "New York", "10001"));
        members.add(new Member("janedoe", "pass456", "jane@example.com", "Jane", "Doe", "456 Elm St", "Los Angeles", "90001"));
        members.add(new Member("mike1985", "securepass", "mike@example.com", "Michael", "Johnson", "789 Oak Ave", "Chicago", "60601"));
        members.add(new Member("emily_smith", "emilypass", "emily@example.com", "Emily", "Smith", "10 Park Rd", "San Francisco", "94101"));
        members.add(new Member("alexwalker", "abc123", "alex@example.com", "Alex", "Walker", "777 Pine St", "Seattle", "98101"));
        members.add(new Member("lisa_miller", "lisa456", "lisa@example.com", "Lisa", "Miller", "999 Broadway", "Boston", "02101"));
        members.add(new Member("davidwilson", "davidpass", "david@example.com", "David", "Wilson", "555 Cedar St", "Miami", "33101"));
        members.add(new Member("sarah_jones", "sarahpass", "sarah@example.com", "Sarah", "Jones", "1234 Maple Ave", "Dallas", "75201"));
        members.add(new Member("ryan_parker", "ryanpass", "ryan@example.com", "Ryan", "Parker", "9876 Oak Rd", "Phoenix", "85001"));
        members.add(new Member("olivia_smith", "olivia123", "olivia@example.com", "Olivia", "Smith", "5432 Pine Rd", "Denver", "80201"));
        return members;
    }


    private List<Car> generateCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry", 100.0, 15));
        cars.add(new Car("Honda", "Civic", 90.0, 10));
        cars.add(new Car("Ford", "Focus", 95.0, 12));
        cars.add(new Car("Chevrolet", "Cruze", 85.0, 8));
        cars.add(new Car("Nissan", "Altima", 110.0, 18));
        cars.add(new Car("Volkswagen", "Golf", 105.0, 14));
        cars.add(new Car("BMW", "3 Series", 150.0, 20));
        cars.add(new Car("Mercedes-Benz", "C-Class", 160.0, 25));
        cars.add(new Car("Audi", "A4", 140.0, 17));
        cars.add(new Car("Lexus", "ES", 125.0, 15));
        cars.add(new Car("Hyundai", "Elantra", 80.0, 7));
        cars.add(new Car("Kia", "Optima", 88.0, 9));
        cars.add(new Car("Mazda", "Mazda6", 92.0, 11));
        cars.add(new Car("Subaru", "Legacy", 95.0, 12));
        cars.add(new Car("Volvo", "S60", 130.0, 16));
        cars.add(new Car("Ford", "Mustang", 160.0, 22));
        cars.add(new Car("Chevrolet", "Camaro", 155.0, 21));
        cars.add(new Car("Dodge", "Charger", 145.0, 18));
        cars.add(new Car("Jeep", "Wrangler", 120.0, 14));
        cars.add(new Car("Toyota", "Corolla", 85.0, 8));
        cars.add(new Car("Honda", "Accord", 95.0, 10));
        cars.add(new Car("Nissan", "Sentra", 78.0, 7));
        cars.add(new Car("Volkswagen", "Jetta", 82.0, 8));
        cars.add(new Car("Ford", "Escape", 110.0, 15));
        cars.add(new Car("Chevrolet", "Equinox", 115.0, 16));
        cars.add(new Car("Toyota", "Rav4", 120.0, 17));
        cars.add(new Car("Honda", "CR-V", 125.0, 18));
        cars.add(new Car("Nissan", "Rogue", 115.0, 16));
        cars.add(new Car("Jeep", "Grand Cherokee", 150.0, 22));
        cars.add(new Car("Subaru", "Outback", 130.0, 19));
        cars.add(new Car("Mazda", "CX-5", 135.0, 20));
        cars.add(new Car("Kia", "Sorento", 120.0, 18));
        cars.add(new Car("Hyundai", "Tucson", 110.0, 15));
        cars.add(new Car("Ford", "Explorer", 155.0, 22));
        cars.add(new Car("Chevrolet", "Traverse", 150.0, 21));
        cars.add(new Car("Toyota", "Highlander", 140.0, 19));
        cars.add(new Car("Honda", "Pilot", 145.0, 20));
        cars.add(new Car("Nissan", "Pathfinder", 135.0, 18));
        cars.add(new Car("Jeep", "Compass", 95.0, 10));
        cars.add(new Car("Subaru", "Forester", 120.0, 14));
        cars.add(new Car("Mazda", "CX-9", 155.0, 20));
        cars.add(new Car("Kia", "Telluride", 150.0, 18));
        cars.add(new Car("Hyundai", "Palisade", 160.0, 21));
        cars.add(new Car("Ford", "F-150", 180.0, 25));
        cars.add(new Car("Chevrolet", "Silverado", 175.0, 24));
        cars.add(new Car("Ram", "1500", 185.0, 26));
        cars.add(new Car("GMC", "Sierra", 170.0, 23));

        return cars;
    }


}
