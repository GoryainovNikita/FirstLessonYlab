package org.example.model.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aop.annotations.Audit;
import org.example.entity.user.User;
import org.example.entity.user.userDTO.UserDTO;
import org.example.model.service.UserLogin;
import org.example.view.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Audit(value = "Вошёл в приложение")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = UserLogin.login(req.getParameter("login"), req.getParameter("password"));

        if (user == null) {
            req.setAttribute("text", "Данного пользователя не существует, либо вы ввели неправильный логин и пароль");
        }
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getLogin());
        View.hello(user.getFirstName(), user.getLastName());
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        byte[] bytes = objectMapper.writeValueAsBytes(userDTO);
        resp.getOutputStream().write(bytes);

        req.getRequestDispatcher("/mainMenu").forward(req,resp);

    }
}
