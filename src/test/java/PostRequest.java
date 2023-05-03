import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequest extends BaseUrl {

    @Test
    public void validRequest() {
        spec.pathParam("first", "users");

        Pojo user = new Pojo("Pedro", "Tester");

        ValidatableResponse response = given().
                contentType(ContentType.JSON).
                spec(spec).
                body(user).
                post("{first}").
                then().statusCode(201)
                .body("name", Matchers.is(user.getName()))
                .body("job", Matchers.is(user.getJob()))
                .body("id", Matchers.isA(String.class))
                .body("createdAt", Matchers.isA(String.class));
    }

    @Test
    public void invalidMethod() {
        spec.pathParam("first", "users");

        Pojo user = new Pojo("Pedro", "Tester");

        ValidatableResponse response = given().
                contentType(ContentType.JSON).
                spec(spec).
                body(user).
                put("{first}").
                then().statusCode(404);
    }

    @Test
    public void withoutJob() {
        spec.pathParam("first", "users");

        Pojo user = new Pojo();
        user.setName("Pedro");

        ValidatableResponse response = given().
                contentType(ContentType.JSON).
                spec(spec).
                body(user).
                post("{first}").
                then().statusCode(201)
                .body("name", Matchers.is(user.getName()))
                .body("job", Matchers.is(user.getJob()))
                .body("id", Matchers.isA(String.class))
                .body("createdAt", Matchers.isA(String.class));
    }

    @Test
    public void withoutName() {
        spec.pathParam("first", "users");

        Pojo user = new Pojo();
        user.setJob("Tester");

        ValidatableResponse response = given().
                contentType(ContentType.JSON).
                spec(spec).
                body(user).
                post("{first}").
                then().statusCode(201)
                .body("name", Matchers.is(user.getName()))
                .body("job", Matchers.is(user.getJob()))
                .body("id", Matchers.isA(String.class))
                .body("createdAt", Matchers.isA(String.class));
    }

    @Test
    public void withoutNameAndJob() {
        spec.pathParam("first", "users");

        Pojo user = new Pojo();

        ValidatableResponse response = given().
                contentType(ContentType.JSON).
                spec(spec).
                body(user).
                post("{first}").
                then().statusCode(201)
                .body("name", Matchers.is(user.getName()))
                .body("job", Matchers.is(user.getJob()))
                .body("id", Matchers.isA(String.class))
                .body("createdAt", Matchers.isA(String.class));
    }
}
