package task.dao;

public class Client extends AbstractDao {
    private String name;
    private String email;

    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                super.toString() +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}