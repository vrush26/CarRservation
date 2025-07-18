package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarRentalSystem {

    private List<Car> cars = new LinkedList<>();
    private List<CarReservation> reservations = new ArrayList<>();
    LocalDateTime reservationEndDate;

    public void addCar(Car car) {
        cars.add(car);
    }

    public CarReservation reserveCar(int days, String carType, LocalDateTime reservationStartDate) throws Exception {
        reservationEndDate = reservationStartDate.plusDays(days);
        for (Car car : getCarsByType(carType)) {
            CarReservation carReservation = new CarReservation(days, reservationStartDate, carType);
            if (isCarAvailable(reservationStartDate, car, days)) {
                reservations.add(carReservation);
                cars.remove(car);
                return carReservation;
            }
        }
        throw new Exception("No available " + carType + " cars for the requested period.");

    }

    private boolean isCarAvailable(LocalDateTime startDate, Car car, int days) {
        reservationEndDate = startDate.plusDays(days);
        for (CarReservation carReservation : reservations) {
            if ((carReservation.getCar().equals(car.getCarType()) && carReservation.isOverLap(startDate, reservationEndDate)) && !cars.contains(car)) {
                return false;
            }
        }
        return true;

    }

    private List<Car> getCarsByType(String carType) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getCarType().equalsIgnoreCase(carType)) {
                result.add(car);
            }
        }
        return result;
    }

}

