package com.mateus.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError {

    private Instant timeSInstant;

    private Integer status;

    private String error;

    private String message;

    private String path;
}
