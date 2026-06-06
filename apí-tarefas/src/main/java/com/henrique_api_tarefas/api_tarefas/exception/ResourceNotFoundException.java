package com.henrique_api_tarefas.api_tarefas.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensage){
        super(mensage);
    }

}
