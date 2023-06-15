package it.bstz.datafaker;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import it.bstz.datafaker.entities.User;
import it.bstz.datafaker.resources.UserResource;
import jakarta.ws.rs.core.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {

    @BeforeAll
    public static void initDb() {
        // Initialize a new Faker
        Faker faker = new Faker();

        // Build a list of Users using faker's fake data
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User u = new User();
            u.setUsername(faker.name().username());
            u.setFirstName(faker.name().firstName());
            u.setLastName(faker.name().lastName());
            u.setBirthday(faker.date().birthday().toLocalDateTime().toLocalDate());
            users.add(u);
        }

        // Open a new transaction to handle jdbc data
        QuarkusTransaction.requiringNew().run(() -> {
            User.deleteAll();
            User.persist(users);
        });
    }

    @Test
    public void fetchAll() {
        List<User> responseBody =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(Response.Status.OK.getStatusCode())
                        .extract()
                        .body().as(List.class);
        Assertions.assertEquals(100, responseBody.size());
    }
}
