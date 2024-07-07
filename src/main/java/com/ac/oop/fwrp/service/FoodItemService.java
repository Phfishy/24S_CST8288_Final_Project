package com.ac.oop.fwrp.service;

import com.ac.oop.fwrp.dao.FoodItemDao;
import com.ac.oop.fwrp.model.FoodItem;
import java.sql.SQLException;
import java.util.List;

public class FoodItemService {
  private final FoodItemDao foodItemDao = new FoodItemDao();
  private final AlertService alertService = new AlertService();
  public FoodItem addFoodItem(FoodItem item) throws SQLException {
    return foodItemDao.createFoodItem(item);
  }

  public FoodItem getFoodItemById(Long id) throws SQLException {
    return foodItemDao.getFoodItemById(id);
  }

  public void updateFoodItem(FoodItem item) throws SQLException {
    foodItemDao.updateFoodItem(item);
  }

  public List<FoodItem> getFoodItemsByRetailerId(long retailerId) throws SQLException {
    return foodItemDao.getFoodItemsByRetailerId(retailerId);
  }

  public List<FoodItem> getAvailableDonations() throws SQLException {
    return foodItemDao.getFoodItemsByDonationStatus(true);
  }

  public List<FoodItem> getDiscountedItems() throws SQLException {
    return foodItemDao.getDiscountedFoodItems();
  }

  public void deleteFoodItem(Long id) throws SQLException {
    foodItemDao.deleteFoodItem(id);
  }

  public void markAsSurplus(Long id) throws SQLException {
    foodItemDao.markAsSurplus(id);
    FoodItem foodItem = foodItemDao.getFoodItemById(id);
    //trigger alerts for this newly marked surplus food item
    alertService.sendAlertsForSurplusItem(foodItem);
  }
}
