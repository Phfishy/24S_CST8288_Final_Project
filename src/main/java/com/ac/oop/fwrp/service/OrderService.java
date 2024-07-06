package com.ac.oop.fwrp.service;

import com.ac.oop.fwrp.dao.OrderDao;
import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.model.Order;
import java.sql.SQLException;

public class OrderService {
  private OrderDao orderDao = new OrderDao();
  private FoodItemService foodItemService = new FoodItemService();

  public void purchaseFoodItem(Long foodItemId, Long userId, Integer quantity) throws SQLException {
    FoodItem foodItem = foodItemService.getFoodItemById(foodItemId);
    if (foodItem.getQuantity() < quantity) {
      throw new RuntimeException("Not enough quantity");
    }
    foodItem.setQuantity(foodItem.getQuantity() - quantity);
    foodItemService.updateFoodItem(foodItem);
    Order order = new Order();
    order.setItemId(foodItemId);
    order.setBuyerId(userId);
    order.setQuantity(quantity);
    order.setTransactionType(1);
    orderDao.createOrder(order);
  }

  public void claimFoodItem(Long foodItemId, Long userId, Integer quantity) throws SQLException {
    FoodItem foodItem = foodItemService.getFoodItemById(foodItemId);
    if (foodItem.getQuantity() < quantity) {
      throw new RuntimeException("Not enough quantity");
    }
    foodItem.setQuantity(foodItem.getQuantity() - quantity);
    foodItemService.updateFoodItem(foodItem);
    Order order = new Order();
    order.setItemId(foodItemId);
    order.setBuyerId(userId);
    order.setQuantity(quantity);
    order.setTransactionType(2);
    orderDao.createOrder(order);
  }
}
