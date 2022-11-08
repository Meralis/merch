package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (InputStream is = req.getInputStream()) {
            byte[] buf = is.readAllBytes();
            String bufStr = new String(buf, StandardCharsets.UTF_8);
            Message msg = Message.fromJSON(bufStr);
            if (msg != null) {
                msgList.add(msg);
            } else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}