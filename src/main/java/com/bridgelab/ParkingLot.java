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
    public Object parkedVehicle = null;

    /**
     * Purpose : parks vehicle if slot is free
     *
     * @param vehicle
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicle != null)
            throw new ParkingLotException("Parking Lot is FULL");
        this.parkedVehicle = vehicle;
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
        try{
            if(this.parkedVehicle.equals(null))
                return true;
            return false;
        }catch (NullPointerException e){
            return true;
        }
    }
}