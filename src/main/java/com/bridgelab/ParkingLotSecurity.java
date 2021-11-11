package com.bridgelab;

/**
 * Purpose : Informing whether parking lot is full
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotSecurity {

    /**
     * Purpose : informing security
     * redirecting into new parking lot
     *
     * @param message : exception message
     * @throws ParkingLotException
     */
    public void informSecurity(String message) throws ParkingLotException {
        redirectIntoNewLot();
    }

    private void redirectIntoNewLot() {
    }
}
