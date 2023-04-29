package by.itacademy.jdbc.starter.servlet;

import by.itacademy.jdbc.starter.service.FlightService;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;


@Slf4j
@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getSession().
                getAttribute("user");
        log.info("User on flights servlet: {}", user);
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter();) {
            writer.write("<h1>Список перелётов</h1>");
            writer.write("<ul>");
            flightService.getDto().stream().forEach(
                    flight -> writer.write("""
                                <li>
                                <a href='/tickets?flightId=%d'>%s</a>
                                </li>
                            """.formatted(flight.getId(), flight.getFlightNo()))
            );
            writer.write("</ul>");
        }
    }
}
