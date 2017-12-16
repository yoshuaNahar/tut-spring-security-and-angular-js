package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

  @LocalServerPort
  private int port;

  private TestRestTemplate template = new TestRestTemplate();

  @Test
  public void resourceProtected() {
    ResponseEntity<String> response = template
        .getForEntity("http://localhost:{port}/", String.class, port);
    // N.B. better if it was UNAUTHORIZED but that means we have to add a custom authentication entry point
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

}
