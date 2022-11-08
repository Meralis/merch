package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;

    private final String from;
    private int n;

    public GetThread(String from) {
        this.from = from;
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                new URL(Utils.getURL() + "/reg?from=" + from);
                URL url = new URL(Utils.getURL() + "/get?startIndex=" + n + "&from=" + from);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                try (InputStream is = http.getInputStream()) {
                    byte[] buf = is.readAllBytes();
                    String strBuf = new String(buf, StandardCharsets.UTF_8);
                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        list.getList().forEach(m -> System.out.println(m));
                        n = list.getLastIndex();
                    }
                }
            }
            Thread.sleep(1000);
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
    }
}
