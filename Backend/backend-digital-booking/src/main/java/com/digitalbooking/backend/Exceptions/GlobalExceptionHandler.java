package com.digitalbooking.backend.Exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger= Logger.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler //Manejador global en caso de que no recaiga en ninguna otra excepcion
    @ResponseBody
    public ErrorMessage allErrors(Exception ex, HttpServletRequest req){
        return logErrorMessage(req.getRequestURI(),ex.getMessage(), ex.getStackTrace());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ErrorMessage notFoundException(HttpServletRequest req,Exception ex){
        return logErrorMessage(req.getRequestURI(),ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidIdException.class})
    @ResponseBody
    public ErrorMessage invalidIDException(HttpServletRequest req,Exception ex){
        return logErrorMessage(req.getRequestURI(),ex.getMessage());
    }

    private ErrorMessage logErrorMessage(String uri, String errorMessage){
        logger.error("Error en la URI: "+uri+" Error: "+errorMessage);
        return new ErrorMessage(uri,errorMessage);
    }

    private ErrorMessage logErrorMessage(String uri, String errorMessage, StackTraceElement[] stackTrace){
        StringBuilder st = new StringBuilder("Error en la URI: "+uri+" Error: "+errorMessage+" StackTrace: ");
        Arrays.stream(stackTrace).forEach(
                (e)->st.append(e.toString()+"\n")
        );
        logger.error(st);
        return new ErrorMessage(uri,errorMessage);
    }


}
