package app.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Auto {

    private Long id;

    private String brand;

    private String model;

    private String number;

    private Integer year;

    private Double mileage;

    private String sticker; //стикер для наклейки на автомобиль, который оформляется по следующему принципу :CAR - первая буква марки, первая буква модели,номер .

    public Auto(Long id, String brand, String model, String number, Integer year, Double mileage) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.number = number;
        this.year = year;
        this.mileage = mileage;

    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return Objects.equals(id, auto.id) && Objects.equals(brand, auto.brand) && Objects.equals(model, auto.model) && Objects.equals(number, auto.number) && Objects.equals(year, auto.year) && Objects.equals(mileage, auto.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, number, year, mileage);
    }

    @Override
    public String toString() {
        return String.format("id- %d, brand - %s, model - %s, number - %s, year - %d, mileage - %s, sticker-%s", id, brand, model, number, year, mileage, sticker);
    }
}
