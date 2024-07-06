package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.model.User;
import com.ac.oop.fwrp.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
  private UserService userService = new UserService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getPathInfo();
    switch (action) {
      case "/register":
        registerUser(request, response);
        break;
      case "/login":
        loginUser(request, response);
        break;
      case "/logout":
        logoutUser(request, response);
        break;
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        break;
    }
  }

  private void registerUser(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    User user = new User();
    user.setName(request.getParameter("name"));
    user.setEmail(request.getParameter("email"));
    user.setType(Integer.parseInt(request.getParameter("type")));
    user.setPassword(request.getParameter("password"));
    user.setAddress(request.getParameter("address"));
    user.setContractNumber(request.getParameter("contractNumber"));

    try {
      User registeredUser = userService.registerUser(user);
      request.setAttribute("user", registeredUser);
      request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
  }

  private void loginUser(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      User user = userService.loginUser(email, password);
      if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/dashboard");
      } else {
        request.setAttribute("error", "Invalid email or password");
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
      }
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
  }

  private void logoutUser(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    response.sendRedirect(request.getContextPath() + "/login");
  }
}
