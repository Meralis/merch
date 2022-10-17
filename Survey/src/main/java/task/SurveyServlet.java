package task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class SurveyServlet extends HttpServlet {

    private ApplicationContext applicationContext;


    public SurveyServlet() {
        this.applicationContext = ApplicationContext.getApplicationContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> currentAnswers = new LinkedList<>();
        collectAnswers(currentAnswers, "awake", request);
        collectAnswers(currentAnswers, "sleep", request);
        String login = (String) request.getSession().getAttribute("user_login");
        ConcurrentMap<String, Integer> globalStatistics = applicationContext.getGlobalStatistics();
        updateStatistics(globalStatistics, currentAnswers);
        ConcurrentMap<String, Integer> currentUserStatistics = applicationContext.getUserStatistics().get(login);
        updateStatistics(currentUserStatistics, currentAnswers);
        responseOnSurvey(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(true);
        if ("exit".equals(a) && (session != null)) {
            session.removeAttribute("user_login");
            request.getSession(false);
            response.sendRedirect("index.jsp");
        }
    }

    private void collectAnswers(Collection<String> list, String questionId, HttpServletRequest request) {
        String answer = request.getParameter(questionId);
        if (answer != null) {
            list.add(answer);
        }
    }

    private void updateStatistics(ConcurrentMap<String, Integer> statistic, Collection<String> answers) {
        answers.forEach(a -> statistic.compute(a, (key, val) -> val == null ? 1 : val + 1));
    }

    public static void responseOnSurvey(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("user_login");
        ConcurrentMap<String, Integer> currentUserStatistics = ApplicationContext.getApplicationContext().getUserStatistics().get(login);
        request.setAttribute("question1answerA", ApplicationContext.getApplicationContext().getGlobalStatistics().get("question1answerA"));
        request.setAttribute("question1answerB", ApplicationContext.getApplicationContext().getGlobalStatistics().get("question1answerB"));
        request.setAttribute("question2answerA", ApplicationContext.getApplicationContext().getGlobalStatistics().get("question2answerA"));
        request.setAttribute("question2answerB", ApplicationContext.getApplicationContext().getGlobalStatistics().get("question2answerB"));
        request.setAttribute("q1aA", currentUserStatistics.get("question1answerA"));
        request.setAttribute("q1aB", currentUserStatistics.get("question1answerB"));
        request.setAttribute("q2aA", currentUserStatistics.get("question2answerA"));
        request.setAttribute("q2aB", currentUserStatistics.get("question2answerB"));
        request.getServletContext().getRequestDispatcher("/survey.jsp").forward(request, response);
    }
}


