package com.bridgelab;

/**
 * Purpose : To segregate the setParkingLotCapacity() and
 * setParkingCapacityAvailable() method for parking lot system
 */
public interface ParkingLotObserver {
    void capacityFull();
    void capacityAvailable();
}