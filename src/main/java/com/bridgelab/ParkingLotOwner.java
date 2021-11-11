package com.bridgelab;

/**
 * Purpose : Informing owner when parking lot if full
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner {
    private static ParkingLotSecurity parkingLotSecurity;

    /**
     * Purpose : Informing owner when parking lot is full
     *
     * @param message : exception message
     * @throws ParkingLotException
     */
    public static void lotFull(String message) throws ParkingLotException {
        parkingLotSecurity.informSecurity("Parking Lot is FULL");
        throw new ParkingLotException(message);
    }
}
