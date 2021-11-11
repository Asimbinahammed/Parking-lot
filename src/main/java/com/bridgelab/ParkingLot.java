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

    public ParkingLot(int capacity) {
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicle != null)
            throw new ParkingLotException("Parking Lot is FULL");
        this.parkedVehicle = vehicle;
        currentCapacity++;
    }

    /**
     * Purpose : Unpark vehicle from slot
     *
     * @param vehicle
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        try {
            if (parkedVehicle.equals(vehicle))
                parkedVehicle = null;
            currentCapacity--;
        } catch (Exception e) {
            throw new ParkingLotException("Parking slot does not have given vehicle");
        }
    }

    /**
     * Purpose : Check vehicle is parked.
     *
     * @param vehicle
     * @return vehicle parked or not
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.parkedVehicle.equals(vehicle);
    }

    /**
     * Purpose : Check vehicle is Unparked.
     *
     * @return vehicle can unparked or not
     */
    public boolean isVehicleUnParked() throws Exception {
        try {
            return this.parkedVehicle.equals(null);
        } catch (NullPointerException e) {
            return true;
        }
    }

    /**
     * Purpose : Check whether parking lot is full or not
     *
     * @return true if parking lot if full or else false
     * @throws ParkingLotException
     */
    public boolean isSlotFull() throws ParkingLotException {
        if (this.currentCapacity >= actualCapacity) {
            ParkingLotOwner.lotFull("Parking Lot is FULL");
            return true;
        }
        return false;
    }

    /**
     * Purpose : Setting owner
     *
     * @param owner : parking lot owner
     */
    public void setOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }
}