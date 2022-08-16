package com.mateus.exceptions.handler;

import com.mateus.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;


@AutoConfigureTestDatabase
class ResourceExceptionHandlerTest {

    @InjectMocks
    ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void objectNotFoundException() {
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .objectNotFoundException(
                        new ObjectNotFoundException("Object Not Found!"),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Object Not Found!", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void validation() {
        MethodParameter methodParameter= mock(MethodParameter.class);
        BindingResult bindingResult = mock(BindingResult.class);

        ResponseEntity<ValidationError> response = resourceExceptionHandler
                .validation(new MethodArgumentNotValidException(methodParameter, bindingResult));

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(400, response.getStatusCodeValue());

    }
}