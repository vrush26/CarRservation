package test;

import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class CarReservationTest {

    private CarRentalSystem system;
    private LocalDate today;

    @BeforeEach
    public void setUp() {
        system = new CarRentalSystem();
        today = LocalDate.now();

        system.addCar(new Sedan("S1"));
        system.addCar(new Sedan("S2"));
        system.addCar(new Suv("SU1"));
        system.addCar(new Van("V1"));
    }

    @Test
    public void testSuccessfulReservation() throws Exception {
        CarReservation res = system.reserveCar(2, "Sedan", today.atStartOfDay());
        assertEquals("Sedan", res.getCar());
    }

    @Test
    public void testReservationConflict() throws Exception {
        system.reserveCar(3, "Van", today.atStartOfDay());
        Exception exception = assertThrows(Exception.class, () -> {
            system.reserveCar(2, "Van", today.plusDays(1).atStartOfDay());
        });
        assertTrue(exception.getMessage().contains("No available Van"));
    }

    @Test
    public void testMultipleReservations() throws Exception {
        CarReservation res1 = system.reserveCar(1, "Sedan", today.atStartOfDay());
        CarReservation res2 = system.reserveCar(1, "Sedan", today.atStartOfDay());

    }

    @Test
    public void testReservationWhenNoneAvailable() throws Exception {
        system.reserveCar(2, "SUV", today.atStartOfDay());
        Exception ex = assertThrows(Exception.class, () -> {
            system.reserveCar(2, "SUV", today.atStartOfDay());
        });
        assertTrue(ex.getMessage().contains("No available SUV"));
    }
}

