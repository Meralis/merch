package task.repository;

import task.dao.Order;

import java.sql.Connection;

public class OrderRepository extends Repository<Order> {
    public OrderRepository(Connection conn, String tableName) {
        super(conn, tableName);
    }

    protected String getConditionForOrderTable() {
        return ", FOREIGN KEY (productId) REFERENCES Products (id)," +
                ", FOREIGN KEY (clientsId) REFERENCES Clients (id),";
    }
}
