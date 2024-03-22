package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionStudent extends RuntimeException{
    public ExceptionStudent(String message, Throwable cause){super(message, cause);}

    public ExceptionStudent(){super();}
}
