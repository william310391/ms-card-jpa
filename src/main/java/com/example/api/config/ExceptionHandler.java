package com.example.api.config;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.api.dto.ResponseDTO;



@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLGrammarException.class)
    public ResponseDTO<String>  handlerSQLGrammarException(SQLGrammarException ex){
        ResponseDTO<String> dto = new ResponseDTO<String>(null);

        dto.setCodigoHTTP(HttpStatus.BAD_REQUEST.value());
        dto.setResultadoDescripcion("Error Interno con servidor de base de datos");
        dto.setResultadoIndicador(0);
        List<String> lista = new ArrayList<String>();
        lista.add(ex.getCause().getMessage());
        dto.setErros(lista);
        return dto;     
        
    }




    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<String> handlerValidateDto(MethodArgumentNotValidException ex){

        ResponseDTO<String> dto = new ResponseDTO<String>(null);

        dto.setCodigoHTTP(HttpStatus.BAD_REQUEST.value());
        dto.setResultadoDescripcion("Excepcion encontrada : el modelo enviado esta imcompleto");
        dto.setResultadoIndicador(0);
        List<String> lista = new ArrayList<String>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            lista.add(fieldName+ " : " + message);
        });
        dto.setErros(lista);
        return dto;    
    }
}
