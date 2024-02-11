package org.example.model.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.meter.MeterWater;
import org.example.entity.meter.MeterWaterDTO;
import org.example.entity.meter.UserMeter;
import org.example.entity.user.AdminPanel;
import org.example.entity.user.Role;
import org.example.entity.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(Integer.parseInt(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("login"), req.getParameter("password"));

        String str = req.getParameter("adminPassword");

        if(str.equals(AdminPanel.getCode())){
            user.setRole(Role.ADMIN);
            resp.sendRedirect("/adminMenu");
        }


    }

}
