package app.zoftwhere.steel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public Connection connection(ApplicationContext context) {
        String url = "jdbc:derby:../derby";

        try {
            return DriverManager.getConnection(url, "public", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
