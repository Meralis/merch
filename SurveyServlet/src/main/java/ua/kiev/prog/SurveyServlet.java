package ua.kiev.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SurveyServlet extends jakarta.servlet.http.HttpServlet {
    ConcurrentHashMap<String, Integer> answers = new ConcurrentHashMap<>();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String course = req.getParameter("course");
        String learning = req.getParameter("learning");
        String days = req.getParameter("days");
        List<String> parameters = Arrays.asList(course, learning, days);
        for (String param : parameters) {
            answers.compute(param, (key, val) -> val == null ? 1 : val + 1);
        }
        req.setAttribute("question1answerA", answers.get("question1answerA"));
        req.setAttribute("question1answerB", answers.get("question1answerB"));
        req.setAttribute("question1answerC", answers.get("question1answerC"));
        req.setAttribute("question2answerA", answers.get("question2answerA"));
        req.setAttribute("question2answerB", answers.get("question2answerB"));
        req.setAttribute("question2answerC", answers.get("question2answerC"));
        req.setAttribute("question3answerA", answers.get("question3answerA"));
        req.setAttribute("question3answerB", answers.get("question3answerB"));
        req.setAttribute("question3answerC", answers.get("question3answerC"));
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
