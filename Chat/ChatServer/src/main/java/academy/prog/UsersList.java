package academy.prog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UsersList {
    public static final UsersList userList = new UsersList();

    public static UsersList getInstance() {
        return userList;
    }

    private final Set<String> usersOnline = Collections.synchronizedSet(new HashSet<>());

    public void connectUser(String from) {
        usersOnline.add(from);
    }

    public void disconnectUser(String from) {
        usersOnline.remove(from);
    }

    public Set<String> getUsers() {
        return usersOnline;
    }
}

