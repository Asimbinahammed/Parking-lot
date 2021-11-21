package com.bridgelab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Purpose : Informing owner when parking lot if full or not.
 * Storing the time of at which vehicle is parked.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner implements ParkingLotObserver {

    static HashMap<Object, String> parkingTime = new HashMap<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private boolean capacityFull;

    /**
     * Purpose : Storing time of vehicle into a hashmap when its park.
     *
     * @param vehicle
     */
    static void parkedTime(Vehicle vehicle) {
        LocalDateTime now = LocalDateTime.now();
        parkingTime.put(vehicle, now.format(formatter));
    }

    /**
     * Purpose : returning parking time by using vehicle as key from hash map.
     * @param vehicle
     * @return parking time as string
     */
    public String getTime(Object vehicle){
        return parkingTime.get(vehicle);
    }


    /**
     * Purpose : Removing vehicle from the list when it unparks.
     *
     * @param vehicle
     */
    public static void removeFromList(Object vehicle) {
        parkingTime.remove(vehicle);
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