package com.bridgelab;

import java.util.ArrayList;

/**
 * Purpose : To list All spot of white cars
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */

public class Police {

    static ArrayList whiteCars = new ArrayList();
    static ArrayList blueToyota = new ArrayList();
    static ArrayList listOfBMW = new ArrayList();

    /**
     * Purpose : to add parking spot into list
     *
     * @param spotNo parking spot number to save into list
     */
    public static void getAllWhiteCars(int spotNo) {
        whiteCars.add(spotNo);
    }

    public static void getAllToyataBlueCars() throws ParkingLotException {
        for (Vehicle vehicle : ParkingLotSystem.vehicles) {
            if( vehicle.getColor().equals("blue") && vehicle.getVehicle().equals("Toyota")){
                int spotNum = ParkingLotSystem.findVehicle(vehicle);
                String numberPlate = vehicle.getNumberPlate();
                blueToyota.add(spotNum,numberPlate);
            }
        }
    }

    public static boolean whiteCarsContains(int spotNumber) {
        return whiteCars.contains(spotNumber);
    }

    public static void listBMW() throws ParkingLotException {
        for (Vehicle vehicle:ParkingLotSystem.vehicles) {
            if(vehicle.getVehicle().equals("BMW")){
                int spotNumber = ParkingLotSystem.findVehicle(vehicle);
                listOfBMW.add(spotNumber);
            }
        }
    }

    

}
