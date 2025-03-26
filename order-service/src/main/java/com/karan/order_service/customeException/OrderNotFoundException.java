package com.karan.order_service.customeException;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException (String message){
        super(message);
    }
}
