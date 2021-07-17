package locations;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationsControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    @Test
    void testListLocations() {
        with()
                .body(new CreateCommand("Szabadka",43.887,21.113))
                .post("/api/locations")
                .then()
                .statusCode(201)
                .body("name",equalTo("Szabadka"))
                .log();
    }



}
