package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.model.User;
import com.ac.oop.fwrp.service.FoodItemService;
import com.ac.oop.fwrp.service.SubscriptionService;
import com.ac.oop.fwrp.service.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
  private FoodItemService foodItemService = new FoodItemService();
  private SubscriptionService subscriptionService = new SubscriptionService();

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }

    User user = (User) session.getAttribute("user");
    List<FoodItem> foodItems;

    try {
      switch (user.getType()) {
        case 5: // Retailer
          foodItems = foodItemService.getFoodItemsByRetailerId(user.getId());
          break;
        case 10: // Charitable Organization
          foodItems = foodItemService.getAvailableDonations();
//          boolean orgHasSubscription = subscriptionService.hasSubscription(user.getId());
//          request.setAttribute("hasSubscription", orgHasSubscription);
          break;
        default: // 1 - Consumer
          foodItems = foodItemService.getDiscountedItems();
//          boolean customerHasSubscription = subscriptionService.hasSubscription(user.getId());
//          request.setAttribute("hasSubscription", customerHasSubscription);
          break;
      }
      request.setAttribute("foodItems", foodItems);
      request.setAttribute("userType", user.getType());
    } catch (SQLException e) {
      request.setAttribute("error", "Error fetching food items: " + e.getMessage());
    }

    request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
  }
}
