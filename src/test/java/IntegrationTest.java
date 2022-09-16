import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {

    private final String ACCOUNT_ID_KEY = "accountId";
    private final String MISSING = String.valueOf(Integer.MAX_VALUE);
    private final String ACCOUNT_ID_VALUE = "#1";
    private final String ID_KEY = "id";
    private final String PERSON_ID_KEY = "personId";
    private final String PERSON_ID_VALUE = "1";

    @BeforeAll
    public static void startApplication() {
        try {
            Main.main(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Test getting account #1 that does not exist.")
    public void testGettingNonExistingAccountReturnsInternalServerError() {
        JSONObject jo = new JSONObject();
        jo.put(ID_KEY, ACCOUNT_ID_VALUE);
        given()
                .baseUri("http://localhost:8080/account")
                .contentType(ContentType.JSON)
                .body(jo.toString())
                .get()
                .then()
                .log()
                .all();
    }

    @Test
    @Order(2)
    @DisplayName("Test adding new account with id #1.")
    public void testAddingNewAccountReturnsOK() {
        JSONObject jo = new JSONObject();
        jo.put(ACCOUNT_ID_KEY, ACCOUNT_ID_VALUE);
        jo.put(PERSON_ID_KEY, PERSON_ID_VALUE);
        given()
                .baseUri("http://localhost:8080/account")
                .contentType(ContentType.JSON)
                .body(jo.toString())
                .post()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(3)
    @DisplayName("Test getting accounts for person with id 1.")
    public void testGettingAccountsForPersonReturnsOK() {
        JSONObject jo = new JSONObject();
        jo.put(ID_KEY, PERSON_ID_VALUE);
        given()
                .baseUri("http://localhost:8080/person")
                .contentType(ContentType.JSON)
                .body(jo.toString())
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(4)
    @DisplayName("Test getting accounts with id #1.")
    public void testGettingAccountByIdReturnsOK() {
        JSONObject jo = new JSONObject();
        jo.put(ID_KEY, ACCOUNT_ID_VALUE);
        given()
                .baseUri("http://localhost:8080/account")
                .contentType(ContentType.JSON)
                .body(jo.toString())
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(5)
    @DisplayName("Test getting account that does not exist.")
    public void testGettingNonExistingAccountByIdReturnsInternalServerError() {
        JSONObject jo = new JSONObject();
        jo.put(ID_KEY, MISSING);
        given()
                .baseUri("http://localhost:8080/account")
                .contentType(ContentType.JSON)
                .body(jo.toString())
                .get()
                .then()
                .log().all()
                .statusCode(500);
    }

}
