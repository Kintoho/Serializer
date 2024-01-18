import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManipulate {

    public static void createTableStudents(Connection conn) throws SQLException {
        String createSql = "CREATE TABLE Students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "group_id INTEGER," +
                "name TEXT NOT NULL," +
                "age Integer NOT NULL," +
                "FOREIGN KEY (group_id) REFERENCES Groups(id))";
        Statement stmt = conn.createStatement();
        stmt.execute(createSql);
    }

    public static void createTableGroups(Connection conn) throws SQLException {
        String createSql = "CREATE TABLE Groups (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "group_number INTEGER NOT NULL)";
        Statement stmt = conn.createStatement();
        stmt.execute(createSql);
    }

    public static void insertGroup(Connection conn, int groupNumber) throws SQLException {
        String insertQuery = "INSERT INTO Groups (group_number) VALUES (?)";
        PreparedStatement prepstmt = conn.prepareStatement(insertQuery);
        prepstmt.setInt(1, groupNumber);
        prepstmt.executeUpdate();
    }

    public static void insertStudent(Connection conn, int group_id, String name, int age) throws SQLException {
        String insertQuery = "INSERT INTO Students (group_id, name, age) VALUES (?, ?, ?)";
        PreparedStatement prepstmt = conn.prepareStatement(insertQuery);
        prepstmt.setInt(1, group_id);
        prepstmt.setString(2, name);
        prepstmt.setInt(3, age);
        prepstmt.executeUpdate();

    }

    public static void deleteTableStudents(Connection conn) throws SQLException {
        String deleteTable = "DROP TABLE Students";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteTable);
    }

    public static void deleteTableGroups(Connection conn) throws SQLException {
        String deleteTable = "DROP TABLE Groups";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteTable);
    }
}