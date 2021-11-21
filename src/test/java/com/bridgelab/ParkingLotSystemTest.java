package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD:src/test/java/com/bridgelab/ParkingLotSystemTest.java

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLot;
=======
class ParkingLotSystemTest {
    ParkingLotSystem ParkingLotSystem;
    ParkingLotSecurity airportSecurity;
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98:src/test/java/com/bridgelab/ParkingLotTest.java
    ParkingLotOwner owner;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
<<<<<<< HEAD:src/test/java/com/bridgelab/ParkingLotSystemTest.java
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
=======
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
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98:src/test/java/com/bridgelab/ParkingLotTest.java
    }

    @Test
<<<<<<< HEAD:src/test/java/com/bridgelab/ParkingLotSystemTest.java
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
=======
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
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98:src/test/java/com/bridgelab/ParkingLotTest.java
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        ParkingLotSystem.setCapacity(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLotAttendant.parkVehicle(vehicle);
        boolean vehicleParked = ParkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(vehicleParked);
    }

    @Test
<<<<<<< HEAD:src/test/java/com/bridgelab/ParkingLotSystemTest.java
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
=======
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
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98:src/test/java/com/bridgelab/ParkingLotTest.java
}