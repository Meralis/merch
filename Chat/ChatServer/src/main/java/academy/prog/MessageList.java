package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private List<Message> list = Collections.synchronizedList(new ArrayList<>());

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public List<Message> getList() {
        return list;
    }

    public synchronized String toJSON(String from, int n) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, from, n));
    }
}
