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
    public Vehicle vehicles;

    public Vehicle(String ownerName, Size size, String vehicle, String numberPlate, String color) {
        this.ownerName = ownerName;
        this.size = size;
        this.vehicle = vehicle;
        this.numberPlate = numberPlate;
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle1 = (Vehicle) o;
        return Objects.equals(vehicle, vehicle1.vehicle) && Objects.equals(numberPlate, vehicle1.numberPlate) && Objects.equals(color, vehicle1.color) && size == vehicle1.size && Objects.equals(ownerName, vehicle1.ownerName);
    }

    public enum Size {LARGE, SMALL, MEDIUM}

}
