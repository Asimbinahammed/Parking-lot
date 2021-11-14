package com.bridgelab;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Purpose : Informing owner when parking lot if full or not.
 * Informing about the time of at which vehicle is parked.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner implements ParkingLotObserver {

    static HashMap<Object, LocalDateTime> timer = new HashMap<>();
    private boolean capacityFull;

    /**
     * Purpose : Storing time of vehicle into a hashmap when its park.
     *
     * @param vehicle
     */
    static void parkedTime(Object vehicle) {
        LocalDateTime now = LocalDateTime.now();
        timer.put(vehicle, now);
    }

    /**
     * Purpose : Removing vehicle from the list when it unparks.
     *
     * @param vehicle
     */
    public static void removeFromList(Object vehicle) {
        timer.remove(vehicle);
    }

    /**
     * Purpose : This method is created to set the status of full capacity of parking lot
     */
    @Override
    public void capacityFull() {
        capacityFull = true;
    }

    /**
     * Purpose : This method is created to set the status of parking capacity available in the parking lot
     */
    @Override
    public void capacityAvailable() {
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

