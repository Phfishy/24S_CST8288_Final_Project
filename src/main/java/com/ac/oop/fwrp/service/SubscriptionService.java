package com.ac.oop.fwrp.service;

import com.ac.oop.fwrp.dao.SubscriptionDao;
import com.ac.oop.fwrp.model.Subscription;

import java.sql.SQLException;

public class SubscriptionService {
    private SubscriptionDao subscriptionDao = new SubscriptionDao();
    public void createSubscription(Subscription subscription) throws SQLException {
        subscriptionDao.createSubscription(subscription);
    }

    public boolean hasSubscription(Long id) {
        return false;
    }
}
