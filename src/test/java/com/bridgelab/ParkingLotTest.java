package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotTest {
    ParkingLot parkingLot;
    Object vehicle;
    ParkingLotOwner owner;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        owner = new ParkingLotOwner();
        parkingLot.setParkingLotObserver(owner);
        vehicle = new Object();
        parkingLot.setCapacity(3);
    }

    @Test
    void givenVehicleAndSlotIsEmpty_whenParked_shouldReturnsTrue() throws ParkingLotException {
        parkingLot.park(vehicle);
        boolean isParked = parkingLot.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenVehicleAndSlotIsNotEmpty_WhenPark_shouldReturnException() throws ParkingLotException {
        parkingLot.setCapacity(1);
        parkingLot.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.park(new Object()));
    }

    @Test
    void givenVehicleWhenUnpark_shouldReturnsTrue() throws Exception {
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isUnParked = parkingLot.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }


    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.unPark(vehicle));
    }

    @Test
    void givenNotFullSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        parkingLot.park(vehicle);
        boolean isFull = owner.isSlotFull();
        Assertions.assertFalse(isFull);
    }

    @Test
    void givenSomeFreeSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenVehicle_whenChecksForLotFull_shouldReturnFalse() throws ParkingLotException {
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenLotFull_whenChecksWithOwner_shouldReturnTrue() throws ParkingLotException {
        parkingLot.setCapacity(1);
        parkingLot.park(vehicle);
        boolean isFull = owner.isSlotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void given2SlotAnd2Vehicle_whenTryToPark_shouldReturnTrue() throws ParkingLotException {
        parkingLot.park(vehicle);
        Object vehicle2 = new Object();
        parkingLot.park(vehicle2);
        boolean check1 = parkingLot.isVehicleParked(vehicle);
        boolean check2 = parkingLot.isVehicleParked(vehicle2);
        Assertions.assertTrue(check1 && check2);
    }

    @Test
    void givenSlotIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        ParkingLotSecurity security = new ParkingLotSecurity();
        parkingLot.setParkingLotObserver(security);
        parkingLot.setCapacity(2);
        parkingLot.park(vehicle);
        Object vehicle2 = new Object();
        parkingLot.park(vehicle2);
        boolean isFull = security.isSlotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void givenLotIsFull_whenUnParksVehicleInformObserver_shouldReturnTrue() throws ParkingLotException {
        Object vehicle2 = new Object();
        parkingLot.setParkingLotObserver(owner);
        parkingLot.setCapacity(2);
        parkingLot.park(vehicle);
        parkingLot.park(vehicle2);
        boolean isFull = owner.isSlotFull();
        Assertions.assertTrue(isFull);
        parkingLot.unPark(vehicle);
        boolean isStillFull = owner.isSlotFull();
        Assertions.assertFalse(isStillFull);
    }

    @Test
    void givenVehicle_whenParkingUsingAttendant_shouldReturnTrue() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean isParked = parkingLot.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenDriver_whenFindingVehicleFromLot_shouldReturnTrue() throws ParkingLotException {
        parkingLot.park(vehicle);
        int indexOfVehicle = parkingLot.findVehicle(vehicle);
        Object vehicleAtIndex = parkingLot.findSpot(indexOfVehicle);
        Assertions.assertEquals(vehicle, vehicleAtIndex);
    }

    @Test
    void givenVehicle_whenChecksForParkingTimeWithCurrentTime_shouldReturnTrue() throws ParkingLotException {
        parkingLot.park(vehicle);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String parkedTime = now.format(formatter);
        Assertions.assertEquals(parkedTime, owner.getTime(vehicle));
    }
}
