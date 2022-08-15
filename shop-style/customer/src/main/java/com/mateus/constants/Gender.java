package com.mateus.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MASCULINO("masculino"),
    FEMININO("feminino");

    Gender(String value) {
        this.value = value;
    }
    private String value;

    @JsonCreator
    public static Gender fromValue(String value) {
        for (Gender sex : Gender.values()) {
            if (sex.value.equalsIgnoreCase(value)) {
                return sex;
            }
        }
        return null;
    }
}
