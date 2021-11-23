package com.bridgelab;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Purpose : To list All spot of white cars
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class Police {
    static ArrayList whiteCars = new ArrayList();
    static ArrayList<Integer> listOfBMW = new ArrayList();
    static ArrayList<Vehicle> toyotaBlue = new ArrayList();
    static ArrayList<Vehicle> listHandicapped = new ArrayList();
    static ArrayList<Vehicle> listSmallHandicapped = new ArrayList();

    /**
     * Purpose : to add parking spot of white cars into list
     *
     * @param spotNo parking spot number to save into list
     */
    public static void getAllWhiteCars(int spotNo) {
        whiteCars.add(spotNo);
    }

    /**
     * Purpose : to check vehicle at spot is white or not
     *
     * @param spotNumber : parked spot number of vehicle
     * @return boolean : true, if list contains that spot.
     */
    public static boolean whiteCarsContains(int spotNumber) {
        return whiteCars.contains(spotNumber);
    }

    /**
     * Purpose : to add parked spot of BMW into list litOfBMW
     *
     * @param spotNumber : Parked vehicle spot
     */
    public static void listOfBMW(Integer spotNumber) {
        listOfBMW.add(spotNumber);
    }

    /**
     * Purpose : checks a vehicle is BMW or not
     *
     * @param spotNumber : Parked vehicle spot
     * @return boolean : true, if list contains the spot
     */
    public static boolean listOfBMWContains(int spotNumber) {
        return listOfBMW.contains(spotNumber);
    }

    /**
     * Purpose : adding all toyota blue cars into list
     *
     * @param vehicle : contains all information about vehicle
     */
    public static void getAllToyotaBlueCar(Vehicle vehicle) {
        toyotaBlue.add(vehicle);
    }

    /**
     * Purpose : check a vehicle is blue toyota or not
     *
     * @param vehicle : contains all information about vehicle
     * @return boolean : true if list contains the vehicle
     */
    public static boolean checkBlueToyota(Vehicle vehicle) {
        return toyotaBlue.contains(vehicle);
    }

    /**
     * Purpose : Used to find if the given vehicle has fraudulent number plate.
     *
     * @param vehicle :  will be used to check the number
     * @return : Returns false if the number is fraudulent.
     */
    public static boolean validateVehicleNumber(Vehicle vehicle) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{2,4}$");
        Matcher matcher = pattern.matcher(vehicle.getNumberPlate());
        return matcher.matches();
    }

    /**
     * Purpose : add into list if given vehicle is small and have handicapped driver
     *
     * @param vehicle : contains all information about vehicle
     */
    public static void listHandicapped(Vehicle vehicle) {
        listHandicapped.add(vehicle);
    }

    /**
     * Purpose : to filter out small vehicle in handicapped spot by their row number and add into new list
     *
     * @param row: row number
     */
    public static void listSmallHandicappedInRows(int row) {
        int[] spotNumbers1 = ParkingLotSystem.vehicleInRow(row);
        for (int spots : spotNumbers1) {
            if (listHandicapped.get(spots).getSize().equals(Vehicle.Size.SMALL))
                listSmallHandicapped.add(listHandicapped.get(spots));
        }
    }

    /**
     * Purpose : to check a vehicle is present in small vehicle with hanicapped driver at fixed rows
     *
     * @param vehicle : contains vehicle details
     * @return boolean : true if list has this vehicle
     */
    public static boolean IsContainInlistSmallHandicappedInRows(Vehicle vehicle) {
        return listSmallHandicapped.contains(vehicle);
    }

}
