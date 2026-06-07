package com.fna.EtudiantApi.exceptions;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String sms){
        super(sms);
    }
}
