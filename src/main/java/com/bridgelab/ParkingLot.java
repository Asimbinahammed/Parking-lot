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
     * Purpose : Setting owner
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
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (vehicles.size() >= actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityFull();
            }
            throw new ParkingLotException("Parking Lot is FULL");
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle is already parked");
        vehicles.add(vehicle);
        ParkingLotOwner.parkedTime(vehicle);
        isLotFull();
    }

    /**
     * Purpose : To inform observer when lot is full
     */
    private void isLotFull() {
        if (vehicles.size() >= actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityFull();
            }
        }
    }

    /**
     * Purpose : Unpark vehicle from slot
     *
     * @param vehicle
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (!vehicles.contains(vehicle))
            throw new ParkingLotException("Vehicle doesn't present here");
        vehicles.remove(vehicle);
        for (ParkingLotObserver observer : observers) {
            observer.capacityAvailabile();
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
     * @return vehicle can unparked or not
     */
    public boolean isVehicleUnParked(Object vehicle) throws Exception {
        try {
            if (!vehicles.contains(vehicle))
                return true;
        } catch (NullPointerException e) {
            throw new ParkingLotException("No given vehicle is present");
        }
        return false;
    }

    /**
     * Purpose : To find spot of vehicle if vehicle is present in parking lot.
     *
     * @param vehicle
     * @return index of vehicle
     * @throws ParkingLotException
     */
    public int findVehicle(Object vehicle) throws ParkingLotException {
        if (vehicles.contains(vehicle))
            return vehicles.indexOf(vehicle);
        throw new ParkingLotException("Vehicle is not present in lot");
    }

    /**
     * Purpose : To fnd vehicle at the given spot.
     *
     * @param vehicleFind : intger value represent spot of parking lot.
     * @return vehicle
     */
    public Object findSpot(int vehicleFind) {
        return vehicles.get(vehicleFind);
    }
}