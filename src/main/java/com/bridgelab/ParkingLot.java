package com.bridgelab;

/**
 * Purpose : To check availability o slot for parking
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
     * @return boolean : vehicle can be parked or not
     */
    public boolean park(Object vehicle) {
        if (this.parkedVehicle != null)
            return false;
        this.parkedVehicle = vehicle;
        return true;
    }

    /**
     * Purpose : Unpark vehicle
     *
     * @param vehicle
     * @return boolean : vehicle can unpark or not
     */
    public boolean unPark(Object vehicle) {

        try {
            if (parkedVehicle.equals(vehicle)) {
                parkedVehicle = null;
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
