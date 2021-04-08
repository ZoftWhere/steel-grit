package app.zoftwhere.steel;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseQuickStart {

    @Test
    void quickStart() throws SQLException {
        // Quick start for Apache Derby.
        try (Connection connection = DriverManager.getConnection("jdbc:derby:../derby;create=true", "public", "")) {
            assert connection != null;
        }
    }

}
