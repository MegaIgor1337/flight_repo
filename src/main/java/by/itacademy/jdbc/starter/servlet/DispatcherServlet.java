package by.itacademy.jdbc.starter.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getSession().
                getAttribute("user");
        log.info("User on dispatcher servlet: {}", user);

//        resp.setContentType("text/html");
//        var dispatcher = req.getRequestDispatcher("/flights");
//        req.setAttribute("dispatcher", true);
//        dispatcher.include(req, resp);
//        var writer = resp.getWriter();
        resp.sendRedirect("/flights");
    }
}
