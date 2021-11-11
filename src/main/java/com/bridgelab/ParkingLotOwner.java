package com.bridgelab;

public class ParkingLotOwner {
    public static void lotFull(String message) throws ParkingLotException {
        throw new ParkingLotException(message);
    }
}
