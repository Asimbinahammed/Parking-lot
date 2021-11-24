package com.bridgelab;

import java.util.Objects;

/**
 * Purpose : To declare vehicle
 *
 * @author : ASIM AHAMMED
 * @since : 09-11-2021
 */
public class Vehicle {
    private final String vehicle;
    private final String numberPlate;
    private final String color;
    private final Size size;
    private final String ownerName;
    private boolean handicapped;
    
    //Declaring enum
    public enum Size {LARGE, SMALL, MEDIUM}

    /*
    Defining vehicle class by using owner name, size, model, number plate, color and is Whether driver is handicapped
    or not
     */
    public Vehicle(String ownerName, Size size, String vehicle, String numberPlate, String color, boolean isHandicapped) {
        this.ownerName = ownerName;
        this.size = size;
        this.vehicle = vehicle;
        this.numberPlate = numberPlate;
        this.color = color;
        this.handicapped = isHandicapped;
    }

    /**
     * Getters for size
     *
     * @return size of vehicle
     */
    public Size getSize() {
        return size;
    }

    /**
     * Getters for owner name
     *
     * @return owner name of vehicle
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Getters for model name
     *
     * @return model name of vehicle
     */
    public String getVehicle() {
        return vehicle;
    }

    /**
     * Getters for number plate
     *
     * @return number plate of vehicle
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * Getters for color of vehicle
     *
     * @return color of vehicle
     */
    public String getColor() {
        return color;
    }

    /**
     * Getters for handicapped
     *
     * @return boolean : true, if handicapped
     */
    public boolean isHandicapped() {
        return handicapped;
    }

    /**
     * Purpose : Checks for equality between two vehicles
     *
     * @param o : vehicle as object
     * @return boolean : true, if two vehicles are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle1 = (Vehicle) o;
        return Objects.equals(vehicle, vehicle1.vehicle) && Objects.equals(numberPlate, vehicle1.numberPlate) && Objects.equals(color, vehicle1.color) && size == vehicle1.size && Objects.equals(ownerName, vehicle1.ownerName);
    }

}
