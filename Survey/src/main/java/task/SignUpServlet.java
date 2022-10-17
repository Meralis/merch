package task;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SignUpServlet extends HttpServlet {

    private ApplicationContext applicationContext;

    public SignUpServlet() {
        this.applicationContext = ApplicationContext.getApplicationContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        applicationContext.addUser(surname, name, age, login, password);
        ConcurrentMap<String, Integer> currentUserAnswers = new ConcurrentHashMap<>();
        List.of("question1answerA", "question1answerB", "question2answerA", "question2answerB").forEach(a -> currentUserAnswers.put(a, 0));
        applicationContext.getUserStatistics().put(login, currentUserAnswers);
        HttpSession session = request.getSession();
        session.setAttribute("user_login", login);
        session.setAttribute("user_password", password);
        response.sendRedirect("survey.jsp");
    }
}



