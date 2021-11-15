package com.bridgelab;

/**
 * Purpose : To guide vehicles to their parking spot.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class ParkingLotAttendant {
    ParkingLot parkingLot = new ParkingLot();

    /**
     * Purpose : Calling park method from parking lot for park vehicle
     *
     * @param vehicle
     * @throws ParkingLotException
     */
    public void parkVehicle(Object vehicle) throws ParkingLotException {
        parkingLot.park(vehicle);
    }
}
