package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.Subscription;
import com.ac.oop.fwrp.model.User;
import com.ac.oop.fwrp.service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/subscription")
public class SubscriptionController extends HttpServlet {
    private SubscriptionService subscriptionService = new SubscriptionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user.getType() != 1 && user.getType() != 10) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        request.getRequestDispatcher("/jsp/subscriptionForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user.getType() != 1 && user.getType() != 10) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        int communicationMethod = Integer.parseInt(request.getParameter("communicationMethod"));
        String foodPreference = request.getParameter("foodPreference");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));

        Subscription subscription = new Subscription();
        subscription.setUserId(user.getId());
        subscription.setCommunicationMethod(communicationMethod);
        subscription.setFoodPreferences(foodPreference);
        subscription.setLatitude(latitude);
        subscription.setLongitude(longitude);

        try {
            subscriptionService.createSubscription(subscription);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}
