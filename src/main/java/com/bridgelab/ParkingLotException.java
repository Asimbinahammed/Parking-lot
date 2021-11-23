package com.bridgelab;

public class ParkingLotException extends Exception {

    public final ExceptionType exceptionType;

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE, VEHICLE_ALREADY_PARKED,
        HANDICAP_PARKING_LOT_IS_FULL }

    public ParkingLotException(ExceptionType exceptionType, String message){
        super(message);
        this.exceptionType = exceptionType;
    }
}
