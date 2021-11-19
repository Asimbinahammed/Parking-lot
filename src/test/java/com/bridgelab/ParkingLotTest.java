package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingLotSystemTest {
    ParkingLotSystem ParkingLotSystem;
    ParkingLotSecurity airportSecurity;
    ParkingLotOwner owner;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new ParkingLotSecurity();
        ParkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Application...!";
        String messageCheck = ParkingLotSystem.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotSystem.park(vehicle);
        boolean isParked = ParkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> ParkingLotSystem.park(vehicle));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class, () -> ParkingLotSystem.unPark(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem.park(vehicle);
        boolean isUnParked = ParkingLotSystem.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotSystem.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        ParkingLotSystem.park(vehicle);
        ParkingLotSystem.park(vehicle2);
        Assertions.assertThrows(Exception.class, () -> ParkingLotSystem.park(vehicle3));
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        ParkingLotSystem.setCapacity(2);
        ParkingLotSystem.park(vehicle);
        ParkingLotSystem.park(vehicle2);
        boolean isVehicleParked1 = ParkingLotSystem.isVehicleParked(vehicle);
        boolean isVehicleParked2 = ParkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isVehicleParked1 && isVehicleParked2);
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotSystem.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        ParkingLotSystem.park(vehicle);
        ParkingLotSystem.park(vehicle2);
        Assertions.assertThrows(ParkingLotException.class, () -> ParkingLotSystem.park(vehicle3));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotSystem.park(vehicle);
        ParkingLotSystem.unPark(vehicle);
        boolean capacityFull = owner.isSlotFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean vehicleParked = ParkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        ParkingLotSystem.setCapacity(3);
        ParkingLotSystem.park(vehicle);
        Object vehicle2 = new Object();
        ParkingLotSystem.park(vehicle2);
        int slotNum = ParkingLotSystem.searchVehicle(vehicle2);
        Assertions.assertEquals(0, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotSystem.park(vehicle);
        String parkTime = ParkingLotSystem.getParkTime(vehicle);
        Assertions.assertEquals(ParkingLotSystem.getDateTime(), parkTime);
    }
}