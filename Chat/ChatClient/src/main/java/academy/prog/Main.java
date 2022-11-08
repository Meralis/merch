package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String from = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your login: ");
            from = scanner.nextLine();
            getUrl("/reg?operation=connected&from=" + from);
            Thread th = new Thread(new GetThread(from));
            th.setDaemon(true);
            th.start();
            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) {
                    break;
                } else if (text.equals("users")) {
                    getUsers();
                } else {
                    int res = buildMessage(text, from).send(Utils.getURL() + "/add");
                    if (res != 200) {
                        System.out.println("HTTP error occurred: " + res);
                        throw new ConnectException("Error code " + res);
                    }
                }
            }
        } finally {
            if (from != null) {
                getUrl("/reg?operation=disconnected&from=" + from);
            }
        }
    }

    private static Message buildMessage(String sourceText, String from) {
        char firstChar = sourceText.charAt(0);
        String userName = "users";
        int messageIndex;
        String text = null;
        if ('@' == firstChar) {
            int i = sourceText.indexOf(" ");
            if (i == -1) {
                userName = sourceText.substring(1);
            } else {
                userName = sourceText.substring(1, i);
            }
            messageIndex = 2 + userName.length();
            if (sourceText.length() > messageIndex) {
                text = sourceText.substring(messageIndex);
            }
        } else {
            text = sourceText;
        }
        return new Message(from, userName, text);
    }

    private static void getUrl(String path) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(Utils.getURL() + path).openConnection();
        conn.setRequestMethod("GET");
        int result = conn.getResponseCode();
        if (result != 200) {
            System.out.println("HTTP error occurred: " + result);
            throw new ConnectException("Error code " + result);
        }
    }

    private static void getUsers() throws IOException {
        URL url = new URL(Utils.getURL() + "/reg?operation=userList");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        int result = http.getResponseCode();
        if (result != 200) {
            System.out.println("HTTP error occurred: " + result);
            throw new ConnectException("Error code " + result);
        }
        try (InputStream is = http.getInputStream()) {
            byte[] buf = is.readAllBytes();
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().create();
            Set<String> userList = gson.<Set<String>>fromJson(strBuf, Set.class);
            System.out.println(userList);
        }
    }
}

