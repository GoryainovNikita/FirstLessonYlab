package org.example.model.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aop.annotations.Audit;
import org.example.entity.meter.MeterWater;
import org.example.entity.meter.MeterWaterDTO;
import org.example.entity.meter.UserMeter;
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


@WebServlet(value = "/currentMeter")
public class CurrentMeterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/currentMeter.jsp").forward(req, resp);
    }

    @Audit(value = "Посмотрел текущие показания")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(Integer.parseInt(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("login"), req.getParameter("password"));

        MeterWater lastMeterWater = UserMeter.getLastMeterWater(user);

        MeterWaterDTO meterWaterDTO = new MeterWaterDTO(lastMeterWater.getColdWater(), lastMeterWater.getHotWater(), lastMeterWater.getDate());
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        byte[] bytes = objectMapper.writeValueAsBytes(meterWaterDTO);
        resp.getOutputStream().write(bytes);

    }
}
