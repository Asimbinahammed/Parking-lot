package com.bridgelab;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : To check availability of slot for parking,
 * to park if slot is free,
 * to unpark from the slot.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotSystem {
    public static List<Vehicle> vehicles;
    private static int actualCapacity;
    private static List<ParkingLotObserver> observers;
    public static List<Vehicle> parkingLot1;
    public static List<Vehicle> parkingLot2;
    private Vehicle Vehicle;


    public ParkingLotSystem() {
        observers = new ArrayList<>();
        vehicles = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
    }

    /**
     * Purpose : Setting observer
     *
     * @param observer : parking lot observer
     */
    public void setParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose : setting new capacity for lot
     *
     * @param capacity : maximum limit of parking lot
=======
/*
 *  Purpose: To Simulate With Parking Lot Problem.
 *
 *  @author ASIM AHAMMED
 *  @since 10-11-2021
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private static List<ParkingSlot> parkingLot1;
    private static List<ParkingSlot> parkingLot2;
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList();
        parkingLot2 = new ArrayList();
    }

    /**
     * Purpose To Print Given Welcome Message
     *
     * @param message Welcome Message
     * @return Welcome Message
     */
    public String welcomeMessage(String message) {
        return message;
    }

    /**
     * Purpose To Set Capacity For Parking Lot
     *
     * @param capacity given as a Slot Capacity
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98
     */
    public void setCapacity(int capacity) {
        actualCapacity = capacity;
    }

    /**
<<<<<<< HEAD
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle for park
     * @throws ParkingLotException : When parking lot is full or when vehicle is not present.
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
=======
     * Purpose To Add Observer In List
     *
     * @param observer Given Observer as a Parameter For add to in List
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose to get Vehicle Parked Time
     *
     * @return Date and Time For Parked Time
     */
    public String getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return myDateObj.format(formatTime);
    }

    /**
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter For Park
     */
    public void park(Object vehicle) throws ParkingLotException {
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98
        if (isVehicleParked(vehicle))
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle Already Parked");

<<<<<<< HEAD
        if (this.parkingLot1.size() == this.actualCapacity && this.parkingLot2.size() == this.actualCapacity) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Lot is full");
            }
        if (parkingLot1.size() > parkingLot2.size()) {
            this.parkingLot2.add(vehicle);
        } else
            this.parkingLot1.add(vehicle);
        ParkingLotOwner.parkedTime(vehicle);
        checkCapacity();
        if(vehicle.getColor().equals("white"))
            Police.getAllWhiteCars(GetPositionByColor(vehicle,"white"));
    }

    /**
     * Purpose : check whether lot is full or not if full then passing into observers
     */
    private void checkCapacity() {
        if (this.parkingLot1.size() == this.actualCapacity && this.parkingLot2.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityFull();
            }
        }
    }

    /**
     * Purpose : Unpark vehicle from slot
     *
     * @param vehicle to unpark
     * @throws ParkingLotException : When vehicle is not present
     * @return boolean : true if vehicle can unpark
     */
public boolean unPark(Object vehicle) throws ParkingLotException {
        if (this.parkingLot1 == null || this.parkingLot2 == null) return false;
        for (Vehicle slot : parkingLot1) {
            if (slot.equals(vehicle)) {
                this.parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityAvailable();
=======
        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }

        ParkingSlot parkingSlot = new ParkingSlot(vehicle, getDateTime());
        if (parkingLot1.size() > parkingLot2.size()) {
            parkingLot2.add(parkingSlot);
        } else
            parkingLot1.add(parkingSlot);

        if (parkingLot1.size() - 1 == actualCapacity && parkingLot2.size() - 1 == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
    }

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as Parameter For Check is Parked Or Not
     * @return If Vehicle contains Given Vehicle
     * it will return True
     */
    public boolean isVehicleParked(Object vehicle) {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        return false;
    }

    /**
     * Purpose To Check given Vehicle is UnParked or Not
     *
     * @param vehicle For Check Vehicle UnParked Or Not
     * @return Boolean type for Vehicle UnPark
     * @throws ParkingLotException If Condition Not Matches Then Throwing Exception Vehicle Not Found
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (parkingLot1 == null || parkingLot2 == null) return false;
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle)) {
                parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98
                }
                return true;
            }
        }
<<<<<<< HEAD
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle)) {
                this.parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityAvailable();
=======
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle)) {
                parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98
                }
                return true;
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

<<<<<<< HEAD


    /**
     * Purpose : Check vehicle is Unparked.
     *
     * @param vehicle to check from list
     * @return vehicle was unparked or not
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !vehicles.contains(vehicle);
    }

    /**
     * purpose : to check vehicle is parked or not
     * @param vehicle : to check from each slot parked list
     * @return boolean : if parked true or else false
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle slot : parkingLot1)
            if (slot.equals(vehicle)) {
                isParked = true;
                break;
            }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle)) {
                isParked = true;
                break;
            }
        }
        return isParked;
    }

    /**
     * Purpose : To find spot of vehicle if vehicle is present in parking lot.
     *
     * @param vehicle to crosscheck with parked list to find parked spot
     * @return spot number of vehicle
     * @throws ParkingLotException : When vehicle is not present
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        for(Vehicle slot:parkingLot1){
            if(slot.equals(vehicle))
                return parkingLot1.indexOf(slot) + 1;
        }
        for(Vehicle slot: parkingLot2){
            if(slot.equals(vehicle))
                return parkingLot2.indexOf(slot) + 1;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not present in parking lot");
    }

    /**
     * purpose : to find spot of vehicle with specific color
     * @param vehicle : to find spot of vehicle
     * @param color : mentioning color tobe filtered out
     * @return spot number
     * @throws ParkingLotException : when o such vehicle found
     */
    public int GetPositionByColor(Vehicle vehicle, String color) throws ParkingLotException {
        for(Vehicle slot: parkingLot1){
          if(slot.equals(vehicle) && slot.getColor().equals(color))
              return parkingLot1.indexOf(slot);
      }
      for(Vehicle slot: parkingLot2){
          if(slot.equals(vehicle) && slot.getColor().equals(color))
              return parkingLot2.indexOf(slot);
      }
       throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No such vehicle found");
    }

=======
    /**
     * Purpose To Search Slot Number For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Slot Number
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public int searchVehicle(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot1.indexOf(slot);
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot2.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

    /**
     * Purpose To Get Park Time For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Park Time
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public String getParkTime(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98
}