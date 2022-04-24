package com.tomorrow.ParkingLotSystem.domain.error;

import java.util.Map;

public class ValidationError extends Error{

    private Map<String, String> fields;

    public ValidationError(String title, int status, String detail, long timestamp, String developerMessage,
                           Map<String, String> fields) {
        super(title, status, detail, timestamp, developerMessage);
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }
}
