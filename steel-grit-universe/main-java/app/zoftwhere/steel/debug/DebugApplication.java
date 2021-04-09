package app.zoftwhere.steel.debug;

import app.zoftwhere.steel.server.MainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Debug application.
 *
 * @since 2.0.0
 */
@SpringBootApplication
public class DebugApplication {

    /**
     * Debug application method.
     *
     * @param args program arguments
     * @since 2.0.0
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
