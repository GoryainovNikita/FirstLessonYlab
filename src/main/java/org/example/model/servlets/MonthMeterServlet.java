package org.example.model.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aop.annotations.Audit;
import org.example.entity.meter.MeterWater;
import org.example.entity.meter.MeterWaterDTO;
import org.example.entity.meter.UserMeter;
import org.example.entity.user.User;
import org.example.model.repository.MeterWaterRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/monthMeter")
public class MonthMeterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/monthMeter.jsp").forward(req, resp);
    }

    @Audit(value = "Посмотрел показания за определенный месяц")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(Integer.parseInt(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("login"), req.getParameter("password"));

        MeterWater month = UserMeter.getSpecificPeriodMeterWater(Integer.parseInt(req.getParameter("month")), user);

        MeterWaterDTO meterWaterDTO = new MeterWaterDTO(month.getColdWater(), month.getHotWater(), month.getDate());
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        byte[] bytes = objectMapper.writeValueAsBytes(meterWaterDTO);
        resp.getOutputStream().write(bytes);

    }
}
