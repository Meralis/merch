package task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ApplicationContext {
    ConcurrentMap<String, User> users = new ConcurrentHashMap<>();
    ConcurrentMap<String, Integer> globalStatistics = new ConcurrentHashMap<>();
    ConcurrentMap<String, ConcurrentMap<String, Integer>> userStatistics = new ConcurrentHashMap<>();

    private ApplicationContext() {
    }

    private static ApplicationContext applicationContext = new ApplicationContext();

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public boolean isAuthenticated(String login, String password) {
        User user = users.get(login);
        if (user == null) {
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }

    public boolean addUser(String surname, String name, String age, String login, String password) {
        User newUser = new User(surname, name, age, login, password);
        User onStore = users.putIfAbsent(login, newUser);
        return newUser == onStore;
    }

    public ConcurrentMap<String, User> getUsers() {
        return users;
    }

    public ConcurrentMap<String, Integer> getGlobalStatistics() {
        return globalStatistics;
    }

    public ConcurrentMap<String, ConcurrentMap<String, Integer>> getUserStatistics() {
        return userStatistics;
    }
}

