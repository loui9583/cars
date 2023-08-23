package dat3.car.cars.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_brand" , nullable = false )
    private String brand;

    @Column(name = "car_model", nullable = false, length = 60)
    private String model;

    @Column(name = "rental_price_per_day")
    private double pricePrDay;

    @Column(name = "max_discount")
    private Integer bestDiscount;

    public Long getId() {
        return id;
    }

    public Car(String brand, String model, double pricePrDay, Integer bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
