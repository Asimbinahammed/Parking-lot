package com.bridgelab;

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
public class ParkingLot {
    public static List<Object> vehicles;
    private static int actualCapacity;
    private static List<ParkingLotObserver> observers;

    public ParkingLot() {
        observers = new ArrayList<>();
        vehicles = new ArrayList<>();
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
     */
    public void setCapacity(int capacity) {
        actualCapacity = capacity;
    }

    /**
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle
     * @throws ParkingLotException : When parking lot is full or when vehicle is not present.
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (vehicles.size() == actualCapacity) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is FULL");
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle is already parked");
        vehicles.add(vehicle);
        ParkingLotOwner.parkedTime(vehicle);
        isLotFull();
    }

    /**
     * Purpose : To inform observer when lot is full
     */
    private void isLotFull() {
        if (vehicles.size() == actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityFull();
            }
        }
    }

    /**
     * Purpose : Unpark vehicle from slot
     *
     * @param vehicle
     * @throws ParkingLotException : When vehicle is not present
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (!vehicles.contains(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle doesn't present here");
        vehicles.remove(vehicle);
        ParkingLotOwner.removeFromList(vehicle);
        for (ParkingLotObserver observer : observers) {
            observer.capacityAvailable();
        }
    }

    /**
     * Purpose : Check vehicle is parked.
     *
     * @param vehicle
     * @return vehicle parked or not
     */
    public boolean isVehicleParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * Purpose : Check vehicle is Unparked.
     *
     * @param vehicle
     * @return vehicle was unparked or not
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return !vehicles.contains(vehicle);
    }

    /**
     * Purpose : To find spot of vehicle if vehicle is present in parking lot.
     *
     * @param vehicle
     * @return spot number of vehicle
     * @throws ParkingLotException : When vehicle is not present
     */
    public int findVehicle(Object vehicle) throws ParkingLotException {
        if (vehicles.contains(vehicle)) {
            int spotNum = vehicles.indexOf(vehicle);
            ParkingLotDriver.spottedAt(spotNum);
            return spotNum;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not present in parking lot");
    }

    /**
     * Purpose : To fnd vehicle at the given spot.
     *
     * @param vehicleFind : integer value represent spot of parking lot.
     * @return vehicle
     */
    public Object findSpot(int vehicleFind) {
        return vehicles.get(vehicleFind);
    }
}