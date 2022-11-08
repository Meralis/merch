package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UsersList userList = UsersList.getInstance();
        try {
            String operation = req.getParameter("operation");
            if (operation == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            switch (operation) {
                case "connected":
                    String from = req.getParameter("from");
                    userList.connectUser(from);
                    break;
                case "disconnected":
                    from = req.getParameter("from");
                    userList.disconnectUser(from);
                    break;
                case "userList":
                    Gson gson = new GsonBuilder().create();
                    String userListAsString = gson.toJson(UsersList.getInstance().getUsers());
                    resp.setContentType("application/json");
                    try (OutputStream os = resp.getOutputStream()) {
                        byte[] buf = userListAsString.getBytes(StandardCharsets.UTF_8);
                        os.write(buf);
                    }
                    break;
                default:
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}