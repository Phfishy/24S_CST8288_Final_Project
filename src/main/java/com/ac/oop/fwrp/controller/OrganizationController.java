package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.FoodItem;
import com.ac.oop.fwrp.service.FoodItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@WebServlet("/organization/*")
public class OrganizationController extends HttpServlet {
  private FoodItemService foodItemService = new FoodItemService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "/claim":
        Long id = Long.parseLong(request.getParameter("id"));
        try {
          request.setAttribute("foodItem", foodItemService.getFoodItemById(id));
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/jsp/foodItemClaim.jsp").forward(request, response);
        break;
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "/claim":
        claimFoodItem(request, response);
        break;

      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  private void claimFoodItem(HttpServletRequest request, HttpServletResponse response) {
    Long id = Long.parseLong(request.getParameter("id"));
  }
}
