package dat3.car.cars.dto;

import dat3.car.cars.entity.Car;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

    private String brand;
    private String model;
    private Double pricePrDay;
    private Integer bestDiscount;

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.brand, c.model, c.pricePrDay, c.bestDiscount);
    }

    public CarRequest(Car m) {
        this.brand = m.getBrand();
        this.model = m.getModel();
        this.pricePrDay = m.getPricePrDay();
        this.bestDiscount = m.getBestDiscount();
    }


}
