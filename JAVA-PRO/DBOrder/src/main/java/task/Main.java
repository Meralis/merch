package task;

import task.dao.Client;
import task.dao.Order;
import task.dao.Product;
import task.repository.Repository;
import task.service.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            deleteExistedTables(conn);
            Repository<Client> clientRepository = new Repository<>(conn, "Clients");
            clientRepository.createTable(Client.class);
            clientRepository.add(new Client("one", "oneMail"));
            clientRepository.add(new Client("two", "twoMail"));

            Repository<Product> productRepository = new Repository<>(conn, "Products");
            productRepository.createTable(Product.class);
            productRepository.add(new Product("prod1", 10, 100));
            productRepository.add(new Product("prod2", 20, 200));

            Repository<Order> orderRepository = new Repository<>(conn, "Orders");
            orderRepository.createTable(Order.class);

            String clientsName = "one";
            String productsName = "prod2";
            int productCount = 6;

            int clientId = clientRepository.getIdFromName(clientsName);
            int productId = productRepository.getIdFromName(productsName);
            orderRepository.add(new Order(clientId, productId, productCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteExistedTables(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("DROP TABLE IF EXISTS Orders");
        st.execute("DROP TABLE IF EXISTS Clients");
        st.execute("DROP TABLE IF EXISTS Products");
    }
}