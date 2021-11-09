package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot service;

    @BeforeEach
    void setUp() {
        service = new ParkingLot();
    }

    @Test
    void givenVehicle_whenSlotIsEmpty_returnsParked() {
        boolean isParked = service.park(new Object());
        Assertions.assertTrue(isParked);
    }
}
