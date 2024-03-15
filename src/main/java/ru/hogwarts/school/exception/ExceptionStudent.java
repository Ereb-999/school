package ru.hogwarts.school.exception;

public class ExceptionStudent extends RuntimeException{
    public ExceptionStudent(String message, Throwable cause){super(message, cause);}

    public ExceptionStudent(){super();}
}
