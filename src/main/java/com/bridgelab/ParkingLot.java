package com.bridgelab;

/**
 * Purpose : To check availability of slot for parking,
 * to park if slot is free,
 * to unpark from the slot.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLot {
    private final int actualCapacity;
    public Object parkedVehicle = null;
    private int currentCapacity;
    private ParkingLotOwner owner;
    private static ParkingLotSecurity parkingLotSecurity;


    public ParkingLot(int capacity) {
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle
     * @return
     */
    public boolean park(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicle != null) {
            owner.capacityFull();
            throw new ParkingLotException("Parking Lot is FULL");
        }
        this.parkedVehicle = vehicle;
        currentCapacity++;
        return false;
    }

    /**
     * Purpose : Unpark vehicle from slot
     *
     * @param vehicle
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        try {
            if (this.parkedVehicle.equals(vehicle)) {
                parkedVehicle = null;
                currentCapacity--;
            }
        } catch (Exception e) {
           throw new ParkingLotException("Vehicle doesnt present here");      }
    }

    /**
     * Purpose : Check vehicle is parked.
     *
     * @param vehicle
     * @return vehicle parked or not
     */
    public boolean isVehicleParked(Object vehicle) {
        if(this.parkedVehicle.equals(vehicle))
            return true;
        return false;
    }

    /**
     * Purpose : Check vehicle is Unparked.
     *
     * @return vehicle can unparked or not
     */
    public boolean isVehicleUnParked() throws Exception {
        try {
            if(this.parkedVehicle.equals(null))
                return true;
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }

    /**
     * Purpose : Setting owner
     *
     * @param owner : parking lot owner
     */
    public void setOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }

    public void avilableLot() throws ParkingLotException {
        if(actualCapacity >= currentCapacity)
            throw new ParkingLotException(actualCapacity - currentCapacity +" available space remaining");
    }
}