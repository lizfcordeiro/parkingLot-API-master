package com.tomorrow.ParkingLotSystem.domain.error;

public class ResourceNotFoundError extends Error{

    public ResourceNotFoundError(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }
}
