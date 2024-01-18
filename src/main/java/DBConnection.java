import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection = null;
    private static String pathDb = "jdbc:sqlite:simple_database.db";

    public static void main(String[] args) throws Exception {

        connection = getConn();

        DBManipulate.createTableGroups(connection);
        DBManipulate.createTableStudents(connection);
        DBManipulate.insertGroup(connection, 345);
        DBManipulate.insertStudent(connection, 1,"Ivanov Ivan Ivanich", 25);
        connection.close();
    }

    private static Connection getConn() throws Exception {
        if (connection == null) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(pathDb);
        }
        return connection;
    }
}