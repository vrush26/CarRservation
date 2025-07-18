package org.example;

import java.time.LocalDateTime;

public abstract class Car {
      private final String carType;


    public Car(String carType) {
        this.carType = carType;

    }


    public String getCarType() {
        return carType;
    }
}
