package academy.prog;

import java.util.LinkedList;
import java.util.List;

public class JsonMessages {
    List<Message> list = new LinkedList<>();
    private int lastIndex;

    public JsonMessages(List<Message> sourceList, String fromLogin, int fromIndex) {
        int i = fromIndex;
        for (; i < sourceList.size(); i++) {
            Message m = sourceList.get(i);
            String to = m.getTo();
            if (("users").equalsIgnoreCase(to) || (fromLogin.equalsIgnoreCase(to))) {
                list.add(m);
            }
        }
        if (list.size() > 0) {
            setLastIndex(i);
        } else {
            setLastIndex(0);
        }
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
}