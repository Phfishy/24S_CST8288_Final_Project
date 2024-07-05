package com.ac.oop.fwrp.service;

import com.ac.oop.fwrp.dao.FoodItemDao;
import com.ac.oop.fwrp.model.FoodItem;

import java.sql.SQLException;
import java.util.Date;

public class FoodItemService {
    private FoodItemDao foodItemDao = new FoodItemDao();

    public FoodItem addFoodItem(FoodItem item) throws SQLException {
        // Add any business logic, validation, etc.
        return foodItemDao.createFoodItem(item);
    }

    public void updateSurplusStatus(Long itemId) throws SQLException {
        FoodItem item = foodItemDao.getFoodItemById(itemId);
        if (item != null) {
            Date oneWeekFromNow = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
            if (item.getExpirationDate().before(oneWeekFromNow)) {
                item.setSurplus(true);
                foodItemDao.updateFoodItem(item);
            }
        }
    }
}
