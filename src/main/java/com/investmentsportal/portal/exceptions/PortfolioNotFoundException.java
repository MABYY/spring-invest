package com.investmentsportal.portal.exceptions;

public class PortfolioNotFoundException extends RuntimeException {
    public PortfolioNotFoundException(){
        super("Portfolio not found");
    }
}
