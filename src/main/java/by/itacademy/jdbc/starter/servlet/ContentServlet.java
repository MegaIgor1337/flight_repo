package by.itacademy.jdbc.starter.servlet;

import by.itacademy.jdbc.starter.mapper.FlightDtoMapperFromFlight;
import by.itacademy.jdbc.starter.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getSession().
                getAttribute("user");
        log.info("User on content servlet: {}", user);
        var flights = flightService.get();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher("/WEB-INF/jsp/content.jsp").forward(req, resp);
    }
}
