package com.bridgelab;


/**
 * Purpose : Informing security when parking lot if full
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotSecurity implements ParkingLotObserver {

    private boolean capacityFull;

    /**
     * Purpose : This method is created to set the status of full capacity of parking lot
     */
    @Override
    public void capacityIsFull() {
        capacityFull = true;
    }

    /**
     * Purpose : This method is created to set the status of parking capacity available in the parking lot
     */
    @Override
    public void capacityIsAvailable() {
        capacityFull = false;
    }

    /**
     * Purpose : To check whether lot is full or not
     *
     * @return true if lot is full
     */
    public boolean isSlotFull() {
        return this.capacityFull;
    }
}
