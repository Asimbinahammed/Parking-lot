package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    public final int capacity = 3;
    ParkingLot service;
    Object vehicle;
    ParkingLotOwner owner;

    @BeforeEach
    void setUp() {
        service = new ParkingLot(capacity);
        owner = new ParkingLotOwner();
        service.setOwner(owner);
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
        service.setCapacity(1);
        service.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> service.park(new Object()));
    }

    @Test
    void givenVehicleWhenUnpark_shouldReturnsTrue() throws Exception {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean isUnparked = service.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnparked);
    }


    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException() throws ParkingLotException {
        Assertions.assertThrows(ParkingLotException.class, () -> service.unPark(vehicle));
    }

    @Test
    void givenFullSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        service.park(vehicle);
        boolean isFull = owner.isSlotFull();
        Assertions.assertFalse(isFull);

    }

    @Test
    void givenSomeFreeSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenAveicle_whenChecksForLotFull_shouldReturnFalse() throws ParkingLotException {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenLotFull_whenChecksWithOwner_shouldReturnTrue() throws ParkingLotException {
        service.setCapacity(1);
        service.park(vehicle);
        boolean isFull = owner.isSlotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void given2SlotAnd2Vehicle_whenTryToPark_shouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        Object vehicle2 = new Object();
        service.park(vehicle2);
        boolean check1 = service.isVehicleParked(vehicle);
        boolean check2 = service.isVehicleParked(vehicle2);
        Assertions.assertTrue(check1 && check2 );
    }
}
