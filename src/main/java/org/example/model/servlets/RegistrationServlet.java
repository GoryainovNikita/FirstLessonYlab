package org.example.model.servlets;

import org.example.aop.annotations.Audit;
import org.example.model.service.UserRegistration;
import org.example.view.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Audit(value = "Зарегистрировался")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!UserRegistration.registration(req.getParameter("firstName"), req.getParameter("secondName"),
                                            req.getParameter("login"),req.getParameter("password"))) {
            req.setAttribute("text", "Данный пользователь уже зарегистрирован");
            req.getRequestDispatcher("/WEB-INF/jsp/enter.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("text", "Поздравляю вы успешно зарегистрированы");
            req.getRequestDispatcher("/WEB-INF/jsp/enter.jsp").forward(req,resp);
        }
    }
}
