package com.investmentsportal.portal.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
