package com.tomorrow.ParkingLotSystem.domain.error;

public class Error {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    public Error(String title, int status, String detail, long timestamp, String developerMessage) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timestamp = timestamp;
        this.developerMessage = developerMessage;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
