package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot service;
    Object vehicle;
    ParkingLotOwner owner;
    public final int capacity = 1;

    @BeforeEach
    void setUp() {
        service = new ParkingLot(capacity);
        owner = new ParkingLotOwner();
        vehicle = new Object();
    }

    @Test
    void givenVehicleAndSlotIsEmpty_whenParked_shouldReturnsTrue() {
        try {
            service.park(vehicle);
            boolean isParked = service.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicleAndSlotIsNotEmpty_WhenPark_shouldReturnFalse() {
        try {
            service.park(vehicle);
            boolean isParked = service.isVehicleParked(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is FULL", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicleWhenUnpark_shouldReturnsTrue() {
        try {
            service.park(vehicle);
            service.unPark(vehicle);
            boolean isUnparked = service.isVehicleUnParked();
            Assertions.assertTrue(isUnparked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicle_whenUnparkAnotherVehicle_returnsFalse() throws Exception {
        try {
            service.park(new Object());
            service.unPark(vehicle);
            boolean isUnParked = service.isVehicleUnParked();
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking slot does not have given vehicle", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnFalse() {
        try {
            service.unPark(vehicle);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking slot does not have given vehicle", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void givenFullSlot_whenChecksForFull_shouldReturnTrue() throws ParkingLotException {
        try{
            service.setOwner(owner);
            service.park(vehicle);
            service.isSlotFull();
        }catch (ParkingLotException e){
            Assertions.assertEquals("Parking Lot is FULL", e.getMessage());
        }
    }

    @Test
    void givenSomeFreeSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        service.setOwner(owner);
        service.park(vehicle);
        service.unPark(vehicle);
        boolean isLotFull = service.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }
}
