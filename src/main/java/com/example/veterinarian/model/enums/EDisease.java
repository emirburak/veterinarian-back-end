package com.example.veterinarian.model.enums;

public enum EDisease {
    DISEASE_BROKE("Broke"),
    DISEASE_HURT("Hurt");

    private String value;

    EDisease(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
