package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Date date1 = new Date();
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ClientDAOImpl2 dao = new ClientDAOImpl2(conn, "Clients");
            dao.createTable(Client.class);
            Client c = new Client("test", 1, 5.2, date1);
            dao.add(c);
            Client c1 = new Client("222", 2, 10.8, date1);
            dao.add(c1);

            int id = c.getId();
            System.out.println(c.getName() + " id= " + id);

            List<Client> allClients = dao.getAll(Client.class);

            dao.getProperties(Client.class, "name", "age");

            allClients.get(0).setAge(55);
            dao.update(allClients.get(0));

            dao.getProperties(Client.class, "age");

            dao.delete(allClients.get(0));
            dao.getAll(Client.class);
        }
    }
}
