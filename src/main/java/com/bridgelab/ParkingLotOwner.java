package com.bridgelab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Purpose : Informing owner when parking lot if full or not.Informing about the time of at which vehicle is parked
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner implements ParkingLotObserver {

    private boolean capacityFull;
    static HashMap<Object, LocalDateTime> timer= new HashMap<Object, LocalDateTime>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");

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

