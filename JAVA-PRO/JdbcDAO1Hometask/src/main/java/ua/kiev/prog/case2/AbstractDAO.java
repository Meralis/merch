package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractDAO<T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public void createTable(Class<T> cls) {
        Field[] fields = cls.getDeclaredFields();
        Field id = getPrimaryKeyField(null, fields);
        StringBuilder sql = new StringBuilder();
        String sqlStr = String.format("CREATE TABLE %s(%s INT AUTO_INCREMENT PRIMARY KEY,", table, id.getName());
        sql.append(sqlStr);
        for (Field f : fields) {
            if (f != id) {
                f.setAccessible(true);
                sql.append(f.getName()).append(" ");
                if (f.getType() == int.class) {
                    sql.append("INT,");
                } else if (f.getType() == String.class) {
                    sql.append("VARCHAR(100),");
                } else if (f.getType() == double.class) {
                    sql.append("DOUBLE,");
                } else if (f.getType().equals(Date.class)) {
                    sql.append("DATETIME,");
                } else
                    throw new RuntimeException("Wrong type");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        try {
            try (Statement st = conn.createStatement()) {
                st.execute(sql.toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void add(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);
            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (Field f : fields) {
                if (f != id) {
                    f.setAccessible(true);
                    if (f.getName().equals("date1")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sDate = sdf.format(f.get(t));
                        names.append(f.getName()).append(',');
                        values.append("STR_TO_DATE(").append("\"").append(sDate).append("\"").append(",").append("\"").append("%Y-%m-%d %H:%i:%s").append("\"),");
                    } else {
                        names.append(f.getName()).append(',');
                        values.append('"').append(f.get(t)).append("\",");
                    }
                }
            }
            names.deleteCharAt(names.length() - 1);
            values.deleteCharAt(values.length() - 1);
            String sql = "INSERT INTO " + table + "(" + names.toString() +
                    ") VALUES(" + values.toString() + ")";
            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
            setIdClient(t, id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setIdClient(T t, Field fieldId) throws SQLException, IllegalAccessException {
        fieldId.setInt(t, getLastId());
    }

    public int getLastId() throws SQLException {
        int lastInsertId = 0;
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                lastInsertId = rs.getInt(1);
            }
            return lastInsertId;
        }
    }

    public void update(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);
            StringBuilder sb = new StringBuilder();
            for (Field f : fields) {
                if (f != id) {
                    f.setAccessible(true);
                    if (f.getName().equals("date1")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sDate = sdf.format(f.get(t));
                        String str = String.format("%s = STR_TO_DATE(\"%s\",\"%s\") ", f.getName(), sDate, "%Y-%m-%d %H:%i:%s");
                        sb.append(str);
                    } else {
                        String str1 = String.format("%s = \"%s\",", f.getName(), f.get(t));
                        sb.append(str1);
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            String sql = "UPDATE " + table + " SET " + sb + " WHERE " +
                    id.getName() + " = \"" + id.get(t) + "\"";
            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);

            String sql = "DELETE FROM " + table + " WHERE " + id.getName() +
                    " = \"" + id.get(t) + "\"";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getAll(Class<T> cls) {
        List<T> result;
        try {
            try (Statement st = conn.createStatement()) {
                String selectAll = "SELECT * FROM " + table;
                result = getSelect(cls, st, selectAll);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    public void getProperties(Class<T> cls, String... names) {
        try (Statement st = conn.createStatement()) {
            String str1 = String.join(",", names);
            String selectWithParam = "SELECT " + str1 + " FROM " + table;
            getSelect(cls, st, selectWithParam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<T> getSelect(Class<T> cls, Statement st, String command) {
        List<T> res = new ArrayList<>();
        try (ResultSet rs = st.executeQuery(command)) {
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                T t = cls.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String columnName = md.getColumnName(i);
                    Field field = cls.getDeclaredField(columnName);
                    field.setAccessible(true);
                    if (field.getName().equals("id")) {
                        field.set(t, rs.getObject(columnName));
                        System.out.print("id = " + rs.getObject(columnName) + "; ");
                    } else if (Date.class.isAssignableFrom(field.getType())) {
                        LocalDateTime ldt = (LocalDateTime) rs.getObject(columnName);
                        Date dt = Date.from(ldt.toInstant(ZoneOffset.UTC));
                        field.set(t, dt);
                        System.out.print("date = " + rs.getObject(columnName) + "; ");
                    } else if (field.getName().equals("age")) {
                        field.set(t, rs.getObject(columnName));
                        System.out.print("age = " + rs.getObject(columnName) + "; ");
                    } else if (field.getName().equals("name")) {
                        field.set(t, rs.getObject(columnName));
                        System.out.print("name = " + rs.getObject(columnName) + "; ");
                    } else if (field.getName().equals("number")) {
                        field.set(t, rs.getObject(columnName));
                        System.out.print("number = " + rs.getObject(columnName) + "; ");
                    }
                }
                res.add(t);
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private Field getPrimaryKeyField(T t, Field[] fields) {
        Field result = null;
        for (Field f : fields) {
            if (f.isAnnotationPresent(Id.class)) {
                result = f;
                result.setAccessible(true);
                break;
            }
        }
        if (result == null)
            throw new RuntimeException("No Id field found");
        return result;
    }
}
