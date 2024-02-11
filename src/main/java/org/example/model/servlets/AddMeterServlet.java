package org.example.model.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aop.annotations.Audit;
import org.example.entity.meter.MeterWater;
import org.example.entity.meter.MeterWaterDTO;
import org.example.entity.meter.UserMeter;
import org.example.entity.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/addMeter")
public class AddMeterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/addMeter.jsp").forward(req, resp);
    }

    @Audit(value = "Добавил показания")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(Integer.parseInt(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("login"), req.getParameter("password"));

        MeterWater meterWater = new MeterWater(Integer.parseInt(req.getParameter("coldWater")),
                Integer.parseInt(req.getParameter("hotWater")), LocalDate.parse(req.getParameter("date")));

        UserMeter.handOverMeterWater(user, meterWater);
    }
}
