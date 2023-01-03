package com.georgyi.clients.fraud;

public record NotificationRequest(Integer toCustomerId, String toCustomerEmail, String message){
}
