package com.fna.EtudiantApi.exceptions;

public class EmailNotExistException extends RuntimeException{
    public EmailNotExistException(String sms){
        super(sms);
    }
}
