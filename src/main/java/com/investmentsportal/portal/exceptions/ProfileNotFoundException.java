package com.investmentsportal.portal.exceptions;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(){
        super("Profile not found");
    }
}
