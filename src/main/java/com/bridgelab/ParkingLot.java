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
    public List vehicles;
    private int actualCapacity;
    private List<ParkingLotObserver> observers;

    public ParkingLot(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList(capacity);
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : Setting owner
     *
     * @param observer : parking lot observer
     */
    public void setParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : setting new capacity for lot
     *
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle
     * @return
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() >= actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityFull();
            }
            throw new ParkingLotException("Parking Lot is FULL");
        }
        if(isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle is already parked");
        this.vehicles.add(vehicle);
        isLotFull();
    }

    /**
     * Purpose : To inform owner when lot i full
     */
    private void isLotFull() {
        if (this.vehicles.size() >= actualCapacity) {
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
        if(!this.vehicles.contains(vehicle))
            throw new ParkingLotException("Vehicle doesn't present here");
        this.vehicles.remove(vehicle);
    }

    /**
     * Purpose : Check vehicle is parked.
     *
     * @param vehicle
     * @return vehicle parked or not
     */
    public boolean isVehicleParked(Object vehicle) {
        if(this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    /**
     * Purpose : Check vehicle is Unparked.
     *
     * @return vehicle can unparked or not
     */
    public boolean isVehicleUnParked(Object vehicle) throws Exception {
        try {
            if(!this.vehicles.contains(vehicle))
                return true;
        } catch (NullPointerException e) {
            throw new ParkingLotException("No given vehicle is present");
        }
        return false;
    }

}