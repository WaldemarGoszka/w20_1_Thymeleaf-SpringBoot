package pl.zajavka.integration;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeesControllerIT extends AbstractIT {
    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate;

    @Test
    public void homePageWorksCorrectly() {
        String url = String.format("http://localhost:%s/zajavka/employees", port);
        String renderedPage = this.restTemplate.getForObject(url, String.class);
        Assertions.assertThat(renderedPage).contains("<title>Employees example</title>");
    }
}
