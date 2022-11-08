package Jdbc1;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/fff111?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";

    static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            try (Statement st = conn.createStatement()) {
                st.execute("DROP TABLE IF EXISTS Apartments");
                st.execute("CREATE TABLE Apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "district VARCHAR(20) NOT NULL, address VARCHAR(20) NOT NULL, " +
                        "square DOUBLE NOT NULL, rooms INT NOT NULL, price INT NOT NULL)");

                initDB("d1", "address1", 95.5, 3, 600);
                initDB("d2", "address2", 18.2, 1, 120);
                initDB("d3", "address3", 130.0, 4, 900);
                initDB("d4", "address4", 54.2, 2, 350);


                Scanner sc = new Scanner(System.in);
                while (true) {
                    System.out.println("1: view apartments");
                    System.out.println("2: select by rooms");
                    System.out.println("3: select by district");
                    System.out.println("4: select by address");
                    System.out.println("5: select by square");
                    System.out.println("6: select by price");
                    System.out.print("-> ");
                    String s = sc.nextLine();
                    switch (s) {
                        case "1" -> viewApartments();
                        case "2" -> selectByRooms();
                        case "3" -> selectByDistrict();
                        case "4" -> selectByAddress();
                        case "5" -> selectBySquare();
                        case "6" -> selectByPrice();
                        default -> {
                            return;
                        }
                    }
                }
            } finally {
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
    }

    private static void initDB(String distr, String addr, Double square, int rooms, int price) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Apartments (district, address, square, rooms, price) VALUES(?, ?, ?, ?, ?)")) {
            ps.setString(1, distr);
            ps.setString(2, addr);
            ps.setDouble(3, square);
            ps.setInt(4, rooms);
            ps.setInt(5, price);
            ps.executeUpdate();
        }
    }

    private static void getData(PreparedStatement ps) throws SQLException {
        ps.execute();
        try (ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "   ");
            System.out.println();
            while (rs.next()) {
                for (int j = 1; j <= md.getColumnCount(); j++) {
                    System.out.print(rs.getString(j) + "     ");
                }
                System.out.println();
            }
        }
    }

    private static void selectByRooms() throws SQLException {
        System.out.println("Enter number of rooms: ");
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        int numberOfRooms = Integer.parseInt(num);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments WHERE rooms = ? ")) {
            ps.setInt(1, numberOfRooms);
            getData(ps);
        }
    }

    private static void selectByDistrict() throws SQLException {
        System.out.println("Enter district: ");
        Scanner scanner = new Scanner(System.in);
        String distr = scanner.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments WHERE district = ? ")) {
            ps.setString(1, distr);
            getData(ps);
        }
    }

    private static void selectByAddress() throws SQLException {
        System.out.println("Enter address: ");
        Scanner scanner = new Scanner(System.in);
        String address = scanner.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments WHERE address = ? ")) {
            ps.setString(1, address);
            getData(ps);
        }
    }

    private static void selectBySquare() throws SQLException {
        System.out.println("Enter minimum square: ");
        Scanner scanner = new Scanner(System.in);
        String sq = scanner.nextLine();
        double square = Double.parseDouble(sq);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments WHERE square > ? ")) {
            ps.setDouble(1, square);
            getData(ps);
        }
    }

    private static void selectByPrice() throws SQLException {
        System.out.println("Enter maximum price: ");
        Scanner scanner = new Scanner(System.in);
        String pr = scanner.nextLine();
        int price = Integer.parseInt(pr);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments WHERE price < ? ")) {
            ps.setInt(1, price);
            getData(ps);
        }
    }

    private static void viewApartments() throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments")) {
            getData(ps);
        }
    }
}