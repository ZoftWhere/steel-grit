package app.zoftwhere.steel;

import app.zoftwhere.steel.controller.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexTest {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private IndexController indexControllerController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testWiring() {
        final String localhost = "http://localhost:" + serverPort;
        assertThat(indexControllerController).isNotNull();
        assertThat(restTemplate.getForObject(localhost + "/", String.class)).isNotNull();
    }
}
