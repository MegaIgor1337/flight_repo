package by.itacademy.jdbc.starter.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/logout")
@Slf4j
public class LogoutServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getSession().
                getAttribute("user");
        log.info("User on logout servlet: {}", user);
        req.getSession().invalidate();
        resp.sendRedirect("/login");
    }

}
