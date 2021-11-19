package com.bridgelab;

/**
 * Purpose : To guide vehicles to their parking spot.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * Purpose : Calling park method from parking lot for park vehicle
     *
     * @param vehicle : to park vehicle.
     * @throws ParkingLotException : if parking lot is full or vehicle already parked
     */
    public void parkVehicle(Object vehicle) throws ParkingLotException {
        parkingLotSystem.park(vehicle);
    }
}
