package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLot;
    ParkingLotOwner owner;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLotSystem();
        owner = new ParkingLotOwner();
        parkingLot.setParkingLotObserver(owner);
        parkingLot.setCapacity(3);
    }

    @Test
    void givenVehicleAndSlotIsEmpty_whenParked_shouldReturnsTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle1);
        Vehicle vehicle2 = new Vehicle("Thomas", Vehicle.Size.LARGE,"BMW", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle2);
        boolean isParked = parkingLot.isVehicleParked(vehicle1);
        boolean isParked2 = parkingLot.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked);
        Assertions.assertTrue(isParked2);
    }

    @Test
    void givenVehicleAndSlotIsNotEmpty_WhenPark_shouldReturnException() throws ParkingLotException {
        parkingLot.setCapacity(1);
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        Vehicle vehicle3 = new Vehicle("Viera", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle3);
        Vehicle car1 = new Vehicle("John", Vehicle.Size.MEDIUM,"BMW", "KL 10 L 15",
                "white");
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.park(car1));
    }

    @Test
    void givenVehicleWhenUnpark_shouldReturnsTrue() throws Exception {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isUnParked = parkingLot.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }


    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException() {
        Vehicle vehicle = null;
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.unPark(vehicle));
    }

    @Test
    void givenNotFullSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        boolean isFull = owner.isSlotFull();
        Assertions.assertFalse(isFull);
    }

    @Test
    void givenSomeFreeSlot_whenChecksForFull_shouldReturnFalse() throws ParkingLotException {
        parkingLot.setCapacity(1);
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenVehicle_whenChecksForLotFull_shouldReturnFalse() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isLotFull = owner.isSlotFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenLotFull_whenChecksWithOwner_shouldReturnTrue() throws ParkingLotException {
        parkingLot.setCapacity(1);
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        Vehicle vehicle2 = new Vehicle("Alba", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle2);
        boolean isFull = owner.isSlotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void given2SlotAnd2Vehicle_whenTryToPark_shouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        Vehicle car1 = new Vehicle("John", Vehicle.Size.MEDIUM,"BMW", "KL 10 L 15",
                "white");
        parkingLot.park(car1);
        boolean check1 = parkingLot.isVehicleParked(vehicle);
        boolean check2 = parkingLot.isVehicleParked(car1);
        Assertions.assertTrue(check1 && check2);
    }

    @Test
    void givenSlotIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        ParkingLotSecurity security = new ParkingLotSecurity();
        parkingLot.setParkingLotObserver(security);
        parkingLot.setCapacity(1);
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        Vehicle car1 = new Vehicle("John", Vehicle.Size.MEDIUM,"BMW", "KL 10 L 15",
                "white");
        parkingLot.park(car1);
        boolean isFull = security.isSlotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void givenLotIsFull_whenUnParksVehicleInformObserver_shouldReturnTrue() throws ParkingLotException {
        Vehicle car1 = new Vehicle("John", Vehicle.Size.MEDIUM,"BMW", "KL 10 L 15",
                "white");
        parkingLot.setParkingLotObserver(owner);
        parkingLot.setCapacity(1);
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        parkingLot.park(car1);
        boolean isFull = owner.isSlotFull();
        Assertions.assertTrue(isFull);
        parkingLot.unPark(vehicle);
        boolean isStillFull = owner.isSlotFull();
        Assertions.assertFalse(isStillFull);
    }

    @Test
    void givenVehicle_whenParkingUsingAttendant_shouldReturnTrue() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLotAttendant.parkVehicle(vehicle);
        boolean isParked = parkingLot.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenDriver_whenFindingVehicleFromLot_shouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        int indexOfVehicle = parkingLot.findVehicle(vehicle);
        Assertions.assertEquals(1, indexOfVehicle);
    }

    @Test
    void givenVehicle_whenChecksForParkingTimeWithCurrentTime_shouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String parkedTime = now.format(formatter);
        Assertions.assertEquals(parkedTime, owner.getTime(vehicle));
    }

    @Test
    void givenHandicapped_whenParks_shouldReturnInNearestSpot() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        int spotNumber = parkingLot.GetPositionByColor(vehicle, "white");
        Assertions.assertEquals(0, spotNumber);
    }
}