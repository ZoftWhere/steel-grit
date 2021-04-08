package app.zoftwhere.steel;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DatabaseQuickStart {

    @Test
    void quickStart() throws SQLException {
        // Quick start for Apache Derby.
        try (Connection connection = DriverManager.getConnection("jdbc:derby:../derby;create=true", "public", "")) {
            assert connection != null;
        }
    }

}
