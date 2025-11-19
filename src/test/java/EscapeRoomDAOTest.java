import db.DBConnection;
import escapeRoom.EscapeRoomDAO;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EscapeRoomDAOTest {
    private static Connection connection;
    private static EscapeRoomDAO dao;

    @BeforeAll
    static void setUpDatabase() throws SQLException {
        connection = DBConnection.getConnection();
        dao = new EscapeRoomDAO(connection);


    }
}
