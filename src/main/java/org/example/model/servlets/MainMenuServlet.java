package org.example.model.servlets;

import org.example.model.service.UserRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/mainMenu")
public class MainMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/mainMenu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int choice = Integer.parseInt(req.getParameter("choice"));

        switch (choice){
            case 1: resp.sendRedirect("/currentMeter");
            case 2: resp.sendRedirect("/addMeter");
            case 3: resp.sendRedirect("/historyMeter");
            case 4: resp.sendRedirect("/monthMeter");
            case 5: resp.sendRedirect("/admin");
        }

    }
}
