package by.itacademy.jdbc.starter.servlet;

import by.itacademy.jdbc.starter.dto.UserDto;
import by.itacademy.jdbc.starter.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getSession().getAttribute("user");
        log.info("User on tickets servlet: {}", user);

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Long flightId = Long.valueOf(req.getParameter("flightId"));

        req.setAttribute("tickets", ticketService.findAllByFlightId(flightId));
        req.getRequestDispatcher("/WEB-INF/jsp/tickets.jsp").forward(req, resp);
    }
}
