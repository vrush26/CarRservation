package org.example;

import java.time.LocalDateTime;
import java.util.Date;

public class CarReservation {
    private int days;
    private String car;
    private LocalDateTime durationStartDate;
    private LocalDateTime durationEndDate;


    public CarReservation(int days, LocalDateTime durationStartDate, String car) {
        this.car = car;
        this.durationStartDate = durationStartDate;
        this.durationEndDate = durationStartDate.plusDays(days);
    }
    public boolean isOverLap(LocalDateTime startDate, LocalDateTime resrvationEndDate) {

        return !(durationEndDate.isBefore(startDate) || startDate.isAfter(resrvationEndDate));

    }
    public LocalDateTime getDurationEndDate() {
        return durationEndDate;
    }

    public LocalDateTime getDurationStartDate() {
        return durationStartDate;
    }

    public String getCar() {
        return car;
    }
}
