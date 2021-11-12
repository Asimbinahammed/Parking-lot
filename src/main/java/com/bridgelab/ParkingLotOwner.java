package com.bridgelab;

/**
 * Purpose : Informing owner when parking lot if full
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner implements ParkingLotObserver{

    private boolean capacityFull;

    /**
     * Purpose : To inform owner lot is full
     */
    public void capacityFull() {
        capacityFull = true;
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
