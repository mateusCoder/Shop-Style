package com.mateus.exceptions.handler;

import com.mateus.exceptions.FieldMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {

    private List<FieldMessage> errors = new ArrayList<>();

    private List<FieldMessage> getErrors(){
        return errors;
    }

    public void addErrors(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
