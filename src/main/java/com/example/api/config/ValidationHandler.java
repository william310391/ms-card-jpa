package com.example.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.api.dto.ResponseDTO;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationHandler extends ResponseEntityExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseDTO<String> handlerMethodArgumentNotValidException2(MethodArgumentNotValidException ex){

        ResponseDTO<String> dto = new ResponseDTO<String>(null);

        dto.setCodigoHTTP(HttpStatus.BAD_REQUEST.value());
        dto.setResultadoDescripcion("Excepcion encontrada : el modelo enviado esta imcompleto");
        dto.setResultadoIndicador(0);
        List<String> lista = new ArrayList<String>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName =  ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            lista.add(fieldName+ " : " + message);
        });
        dto.setErros(lista);
        return dto;    
    }

    
}
