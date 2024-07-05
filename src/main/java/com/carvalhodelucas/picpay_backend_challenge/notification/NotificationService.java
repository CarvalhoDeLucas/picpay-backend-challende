package com.carvalhodelucas.picpay_backend_challenge.notification;

import org.springframework.stereotype.Service;

import com.carvalhodelucas.picpay_backend_challenge.transaction.Transaction;

@Service
public class NotificationService {

    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction) {
        notificationProducer.sendNotification(transaction);
    }

}
