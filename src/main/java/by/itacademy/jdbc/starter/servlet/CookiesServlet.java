package by.itacademy.jdbc.starter.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
@WebServlet("/cookies")
public class CookiesServlet extends HttpServlet {
    private final static String UNIQUE_ID = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = req.getSession().
                getAttribute("user");
        log.info("User on cookies servlet: {}", user);
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var cookies = req.getCookies();
        if (cookies == null ||
            Arrays.stream(cookies).filter(cookie ->
                    UNIQUE_ID.equals(cookie.getName())).findFirst().isEmpty()) {
            var cookie = new Cookie(UNIQUE_ID, "1");
            cookie.setPath("/cookies");
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);

        }
    }
}
