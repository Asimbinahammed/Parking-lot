package com.bridgelab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bridgelab.ParkingLotSystem.vehicles;

/**
 * Purpose : To list All spot of white cars
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class Police {

    static ArrayList whiteCars = new ArrayList();
    static Map<Integer, List<String>> blueToyota = new HashMap<Integer, List<String>>();
    static ArrayList listOfBMW = new ArrayList();
    static ArrayList toyotaBlue = new ArrayList();

    /**
     * Purpose : to add parking spot into list
     *
     * @param spotNo parking spot number to save into list
     */
    public static void getAllWhiteCars(int spotNo) {
        whiteCars.add(spotNo);
    }

    public static boolean whiteCarsContains(int spotNumber) {
        return whiteCars.contains(spotNumber);
    }

    public static void listOfBMW(Integer spotNumber)  {
        listOfBMW.add(spotNumber);
    }

    public static boolean listOfBMWContains(int spotNumber) {
        return listOfBMW.contains(spotNumber);
    }


    public static void getAllToyataBlueCar(Vehicle vehicle) {
        toyotaBlue.add(vehicle);
    }

    public static boolean checkBlueToyota(Vehicle vehicle) {
        return toyotaBlue.contains(vehicle);
    }

    /**
     * Purpose : Used to find if the given vehicle has fraudulent number plate.
     *
     * @param vehicle : Given Vehicle will be used to check the number
     * @return : Returns false if the number is fraudulent.
     */
    public static boolean validateVehicleNumber(Vehicle vehicle) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{2,4}$");
        Matcher matcher = pattern.matcher(vehicle.getNumberPlate());
        return matcher.matches();
    }
}
