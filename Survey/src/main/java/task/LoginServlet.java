package task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private ApplicationContext applicationContext;

    public LoginServlet() {
        this.applicationContext = ApplicationContext.getApplicationContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (applicationContext.isAuthenticated(login, password)) {

            session = request.getSession(true);
            session.setAttribute("user_login", login);
            session.setAttribute("user_password", password);
            SurveyServlet.responseOnSurvey(request, response);

        } else {
            session.setAttribute("sign_in_status", "wrong");
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        if ("signUp".equals(a)) {
            response.sendRedirect("registration.jsp");
        }
    }
}




