package com.bridgelab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Purpose : Informing owner when parking lot if full or not.
 * Storing the time of at which vehicle is parked.
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class ParkingLotOwner implements ParkingLotObserver {

    static HashMap<Object, String> parkingTime = new HashMap<>();
    static ArrayList vehicleParkedInLast30Min = new ArrayList();
    static HashMap<Object, String> parkedVehicleForLast30min = new HashMap<Object, String>();
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
        parkedVehicleForLast30min(vehicle);
    }

    /**
     * Purpose : to create a new list which has details of parked vehicle for last 3o min
     * @param vehicle : contains all information of vehicle
     */
    static void parkedVehicleForLast30min(Vehicle vehicle){
        LocalDateTime parkedVehicle30minBefore = LocalDateTime.now().plusMinutes(-30);
        parkedVehicleForLast30min.put(vehicle, parkedVehicle30minBefore.format(formatter));
        Set<Object> setOfKeys = parkingTime.keySet();
        for (Object key : setOfKeys) {
            if(!parkedVehicleForLast30min.containsKey(key))
                vehicleParkedInLast30Min.add(key);
        }
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
    public static void removeFromList(Vehicle vehicle) {
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