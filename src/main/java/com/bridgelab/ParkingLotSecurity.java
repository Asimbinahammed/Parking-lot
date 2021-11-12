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
     * Purpose : To inform lot is full
     */
    @Override
    public void capacityFull() {
        capacityFull = true;
    }

    @Override
    public void capacityAvailabile() {
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
