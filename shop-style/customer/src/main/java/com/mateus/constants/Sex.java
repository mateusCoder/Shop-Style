package com.mateus.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;

public enum Sex {
    MASCULINO("masculino"),
    FEMININO("feminino");

    Sex(String value) {
        this.value = value;
    }
    private String value;

    @JsonCreator
    public static Sex fromValue(String value) {
        for (Sex sex : Sex.values()) {
            if (sex.value.equalsIgnoreCase(value)) {
                return sex;
            }
        }
        return null;
    }
}
