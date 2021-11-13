package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot service;
    Object vehicle;
    ParkingLotOwner owner;

    @BeforeEach
    void setUp() {
        service = new ParkingLot();
        owner = new ParkingLotOwner();
        service.setParkingLotObserver(owner);
        vehicle = new Object();
        service.setCapacity(3);
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
        boolean isUnParked = service.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }


    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException(){
        Assertions.assertThrows(ParkingLotException.class, () -> service.unPark(vehicle));
    }

    @Test
    void givenNotFullSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
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
    void givenVehicle_whenChecksForLotFull_shouldReturnFalse() throws ParkingLotException {
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

    @Test
    void givenSlotIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        ParkingLotSecurity security = new ParkingLotSecurity();
        service.setParkingLotObserver(security);
        service.setCapacity(2);
        service.park(vehicle);
        Object vehicle2 = new Object();
        service.park(vehicle2);
        boolean isFull = security.isSlotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void givenLotIsFull_whenUnParksVehicleInformObserver_shouldReturnTrue() throws ParkingLotException {
        Object vehicle2 = new Object();
        service.setParkingLotObserver(owner);
        service.setCapacity(2);
        service.park(vehicle);
        service.park(vehicle2);
        boolean isFull = owner.isSlotFull();
        Assertions.assertTrue(isFull);
        service.unPark(vehicle);
        boolean isAvailable = owner.isSlotFull();
        Assertions.assertFalse(isAvailable);
    }

    @Test
    void givenVehicle_whenParkingUsingAttendant_shouldReturnTrue() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean isParked = service.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }
}
