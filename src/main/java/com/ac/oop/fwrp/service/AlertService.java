package com.ac.oop.fwrp.service;

import com.ac.oop.fwrp.dao.AlertDao;
import com.ac.oop.fwrp.dao.SubscriptionDao;
import com.ac.oop.fwrp.dao.UserDao;
import com.ac.oop.fwrp.model.Alert;
import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.model.Subscription;
import com.ac.oop.fwrp.model.User;

import java.sql.SQLException;
import java.util.List;

public class AlertService {
    private SubscriptionDao subscriptionDao = new SubscriptionDao();
    private UserDao userDao = new UserDao();
    private AlertDao alertDao = new AlertDao();
    private EmailService emailService = new EmailService();
    private SmsService smsService = new SmsService();

    public void sendAlertsForSurplusItem(FoodItem foodItem) throws SQLException {
        //send alerts to all subscribers
        List<Subscription> subscriptions = subscriptionDao.getAllSubscriptions();
        for (Subscription subscription : subscriptions) {
            if (isRelevantForSubscriber(foodItem, subscription)) {
                sendAlert(foodItem, subscription);
            }
        }
    }

    private boolean isRelevantForSubscriber(FoodItem foodItem, Subscription subscription) {
        // Check if the food item matches the subscriber's preferences
        return subscription.getFoodPreferences() == null ||
                foodItem.getName().toLowerCase().contains(subscription.getFoodPreferences().toLowerCase()) ||
                foodItem.getDescription().toLowerCase().contains(subscription.getFoodPreferences().toLowerCase());
    }

    private void sendAlert(FoodItem foodItem, Subscription subscription) throws SQLException {
        Alert alert = new Alert();
        alert.setUserId(subscription.getUserId());
        alert.setItemId(foodItem.getId());
        alert.setType(subscription.getCommunicationMethod());
        alert.setAlertTime(new java.util.Date());

        alertDao.createAlert(alert);

        String message = String.format("New surplus food item available: %s", foodItem.getName());

        if (subscription.getCommunicationMethod() == 1) { // Email
            User user = userDao.getUserById(subscription.getUserId()); // You need to implement this method
            emailService.sendEmail(user.getEmail(), "Surplus Food Alert", message);
        } else if (subscription.getCommunicationMethod() == 2) { // SMS
            User user = userDao.getUserById(subscription.getUserId()); // You need to implement this method
            smsService.sendSms(user.getContractNumber(), message);
        }
    }
}
