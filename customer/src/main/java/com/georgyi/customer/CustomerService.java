package com.georgyi.customer;

import com.georgyi.amqp.RabbitMQMessageProducer;
import com.georgyi.clients.fraud.FraudCheckResponse;
import com.georgyi.clients.fraud.FraudClient;
import com.georgyi.clients.fraud.NotificationClient;
import com.georgyi.clients.fraud.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CustomerService{
    @Autowired
    private final CustomerRepository customerRepository;
//    @Autowired
//    private final RestTemplate restTemplate;
    @Autowired
    private final FraudClient fraudClient;
//    @Autowired
//    private final NotificationClient notificationClient;
    @Autowired
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient, NotificationClient notificationClient, RabbitMQMessageProducer rabbitMQMessageProducer){
        this.customerRepository = customerRepository;
        //this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
        //this.notificationClient = notificationClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }
    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

//       FraudCheckResponse forObject =  restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
//                                                                      FraudCheckResponse.class,
//                                                                      customer.getId());
//
       FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

       if(fraudCheckResponse.isFraudster()){
           throw new IllegalStateException("fraudster");
       }

//        notificationClient.sendNotification(new NotificationRequest(customer.getId(),
//                                                                    customer.getEmail(),
//                                                                    String.format("Hi %s, welcome!", customer.getFirstName())));
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName())
        );

        rabbitMQMessageProducer.publish(notificationRequest,"internal.exchange", "internal.notification.routing-key");
    }
}
