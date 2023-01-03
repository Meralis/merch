package task.repository;

import task.dao.AbstractDao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Repository<T extends AbstractDao> {
    private final Connection conn;
    private final String tableName;

    public Repository(Connection conn, String tableName) {
        this.conn = conn;
        this.tableName = tableName;
    }

    public void createTable(Class<T> cls) {
        Field[] fields = cls.getDeclaredFields();
        String fieldList = Arrays.stream(fields)
                .map(f -> String.format("%s %s", f.getName(), (String.class.equals(f.getType())) ? "VARCHAR(10)" : "INT"))
                .collect(Collectors.joining(","));
        String createTableStr = String.format("CREATE TABLE %s(id INT AUTO_INCREMENT PRIMARY KEY, %s %s)", tableName, fieldList, getConditionForOrderTable());
        try {
            conn.createStatement().execute(createTableStr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        String fieldList = Arrays.stream(fields)
                .peek(f -> f.setAccessible(true))
                .map(Field::getName)
                .collect(Collectors.joining(","));
        String questionList = "?,".repeat(fields.length);
        String insertStatementStr = String.format("INSERT INTO %s(%s,id) VALUES(%s?)", tableName, fieldList, questionList);
        try (PreparedStatement ps = conn.prepareStatement(insertStatementStr)) {
            int i = 0;
            while (i < fields.length) {
                Field field = fields[i++];
                if (int.class.equals(field.getType())) {
                    ps.setInt(i, field.getInt(t));
                }
                if (String.class.equals(field.getType())) {
                    ps.setString(i, (String) field.get(t));
                }
            }
            ps.setInt(++i, t.getId());
            ps.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdFromName(String name) {
        String str = String.format("SELECT id FROM %s WHERE name=\"%s\";", tableName, name);
        int result = 0;
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(str);
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getConditionForOrderTable() {
        return "";
    }
}
