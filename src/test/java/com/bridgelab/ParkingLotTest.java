package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot service;
    Object vehicle;
    ParkingLotOwner owner;
    public final int capacity = 3;

    @BeforeEach
    void setUp() {
        service = new ParkingLot(capacity);
        owner = new ParkingLotOwner();
        vehicle = new Object();
        service.setOwner(owner);

    }

    @Test
    void givenVehicleAndSlotIsEmpty_whenParked_shouldReturnsTrue() throws ParkingLotException {
            service.park(vehicle);
            boolean isParked = service.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
    }

    @Test
    void givenVehicleAndSlotIsNotEmpty_WhenPark_shouldReturnException() throws ParkingLotException {
            service.park(vehicle);
            Assertions.assertThrows(ParkingLotException.class, ()-> service.park(new Object()));
    }

    @Test
    void givenVehicleWhenUnpark_shouldReturnsTrue() throws Exception {
            service.park(vehicle);
            service.unPark(vehicle);
            boolean isUnparked = service.isVehicleUnParked();
            Assertions.assertTrue(isUnparked);
    }


    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException() throws ParkingLotException {
            Assertions.assertThrows(ParkingLotException.class, () -> service.unPark(vehicle));
    }

    @Test
    void givenFullSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
            service.setOwner(owner);
            service.park(vehicle);
            boolean isFull = owner.isSlotFull();
            Assertions.assertFalse(isFull);

    }

    @Test
    void givenSomeFreeSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        service.setOwner(owner);
        service.park(vehicle);
        service.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenSomeFreeSlot_whenInformsOwner_shouldReturnRemainingSpace() throws ParkingLotException {
        service.setOwner(owner);
        service.park(vehicle);
        service.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }
}
