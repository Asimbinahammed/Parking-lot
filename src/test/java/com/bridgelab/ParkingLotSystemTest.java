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
        Vehicle vehicle4 = new Vehicle("Henry", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white", true);
        parkingLot.park(vehicle4);
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
        boolean isUnParked = parkingLot.isVehicleParked(vehicle);
        Assertions.assertFalse(isUnParked);
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
        Vehicle vehicle3 = new Vehicle("Xavi", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white", true);
        parkingLot.park(vehicle3);
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
        Assertions.assertFalse(isFull);
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
        Assertions.assertFalse(isFull);
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
    void givenVehicle_whenParks_shouldReturnSpot() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        int spotNumber = parkingLot.getPositionByColor("white");
        Assertions.assertEquals(1, spotNumber);
    }

    @Test
    void givenWhiteVehicle_whenParks_shouldBeListedInPolice() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"benz", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        int spotNumber = parkingLot.getPositionByColor("white");
        boolean checkForWhiteCarsSpot = Police.whiteCarsContains(spotNumber);
        Assertions.assertTrue(checkForWhiteCarsSpot);
    }

    @Test
    void givenBlueToyotaVehicle_whenParks_shouldBeListedInPolice() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"Toyota", "KL 10 LM10",
                "blue");
        parkingLot.park(vehicle);
        boolean checks = Police.checkBlueToyota(vehicle);
        Assertions.assertTrue(checks);
    }

    @Test
    void givenBMW_whenParks_shouldBeListedInPolice() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"BMW", "KL 10 LM10",
                "white");
        parkingLot.park(vehicle);
        int spotNumber = parkingLot.findVehicle(vehicle);
        boolean checkForBMWCarsSpot = Police.listOfBMWContains(spotNumber);
        Assertions.assertTrue(checkForBMWCarsSpot);
    }

    @Test
    void givenVehicle_whenParks_shouldValidatedForNumberPlate() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white");
        parkingLot.park(vehicle);
        boolean checkValidity = Police.validateVehicleNumber(vehicle);
        Assertions.assertTrue(checkValidity);
    }

    @Test
    void givenHandicappedVehicleParked_whenChecked_shouldReturnFirstEmptySpot() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle);
        int spot = parkingLot.findVehicle(vehicle);
        Assertions.assertEquals(1, spot);
    }

    @Test
    void givenHandicapped_whenChecked_returnsParkeSpot() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle);
        int spot = parkingLot.findVehicle(vehicle);
        Assertions.assertEquals(1, spot);
        Vehicle vehicle2 = new Vehicle("Wallence", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle2);
        int spot2 = parkingLot.findVehicle(vehicle2);
        Assertions.assertEquals(2, spot2);
    }

    @Test
    void given8HandicappedDrivers_whenCheckedForSmall_returnsParkeSpot() throws ParkingLotException {
        parkingLot.setCapacity(8);
        Vehicle vehicle = new Vehicle("David", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle);
        Vehicle vehicle2 = new Vehicle("Wallence", Vehicle.Size.SMALL,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("Etoo", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle3);
        Vehicle vehicle4 = new Vehicle("Ibra", Vehicle.Size.SMALL,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle4);
        Vehicle vehicle5 = new Vehicle("Sergio", Vehicle.Size.LARGE,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle5);
        Vehicle vehicle6 = new Vehicle("Dani", Vehicle.Size.SMALL,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle6);
        Vehicle vehicle7 = new Vehicle("Abidal", Vehicle.Size.MEDIUM,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle7);
        Vehicle vehicle8 = new Vehicle("Valdes", Vehicle.Size.SMALL,"BMW", "KL10LM10",
                "white", true);
        parkingLot.park(vehicle8);
        Police.listSmallHandicappedInRows(2);
        Police.listSmallHandicappedInRows(4);
        boolean isListContains1 = Police.IsContainInlistSmallHandicappedInRows(vehicle8);
        boolean isListContains2 = Police.IsContainInlistSmallHandicappedInRows(vehicle4);
        Assertions.assertTrue(isListContains1);
        Assertions.assertTrue(isListContains2);
    }
}