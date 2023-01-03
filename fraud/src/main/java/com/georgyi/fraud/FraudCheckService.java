package com.georgyi.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class FraudCheckService{
    @Autowired
    FraudCheckHistoryRepository checkHistoryRepository;
    public boolean isFraudulentCustomer(Integer customerId){
        checkHistoryRepository.save(
        FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build()
        );
        return false;
    }
}
