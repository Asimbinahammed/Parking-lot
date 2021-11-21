package com.bridgelab;

/**
 * Purpose : To guide vehicles to their parking spot.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class ParkingLotAttendant {
<<<<<<< HEAD
    ParkingLotSystem parkingLot = new ParkingLotSystem();
=======
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98

    /**
     * Purpose : Calling park method from parking lot for park vehicle
     *
     * @param vehicle : to park vehicle.
     * @throws ParkingLotException : if parking lot is full or vehicle already parked
     */
    public void parkVehicle(Object vehicle) throws ParkingLotException {
<<<<<<< HEAD
        parkingLot.park((Vehicle) vehicle);
=======
        parkingLotSystem.park(vehicle);
>>>>>>> 3ccb58afab8c5dd4e984eee48611f9138f732f98
    }
}