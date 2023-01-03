package task.dao;

public abstract class AbstractDao {
    private int id;

    public AbstractDao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractDao{" +
                "id=" + id +
                '}';
    }
}
