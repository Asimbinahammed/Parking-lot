package com.bridgelab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Purpose : Informing owner when parking lot if full or not.Informing about the time of at which vehicle is parked
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner implements ParkingLotObserver {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");
    private boolean capacityFull;

    /**
     * Purpose : Getting time of vehicle when its park.
     *
     * @param vehicle
     */
    static void parkedTime(Object vehicle) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

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

