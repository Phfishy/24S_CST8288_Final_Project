package com.ac.oop.fwrp.controller;

import com.ac.oop.fwrp.service.UserService;

@WebServlet("/user")
public class UserController extends HttpServlet{
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if ("/register".equals(action)) {
            registerUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setType(Integer.parseInt(request.getParameter("type")));
        user.setPassword(request.getParameter("password"));

        try {
            User registeredUser = userService.registerUser(user);
            request.setAttribute("user", registeredUser);
            request.getRequestDispatcher("/jsp/registration-success.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

}
