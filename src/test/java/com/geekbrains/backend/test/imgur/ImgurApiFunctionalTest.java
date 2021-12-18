package com.geekbrains.backend.test.imgur;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@DisplayName("Tests RA")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiFunctionalTest extends ImgurApiAbstractTest {

    private static String CURRENT_IMAGE_ID;


    @DisplayName("Endpoint № 1 Get information about an image.")
    @Test
    @Order(1)
    void getAccountBase() {
       // String userName = "mityidizel";
        ResponseSpecification test_1 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .expectBody("data.id", is("l38bOK3"))
                .expectBody("data.size", is(288535))
                .expectBody("data.type", is("image/jpeg"))
                .expectBody("data.name", is("White Mollienesia!"))
                .expectBody("data.title", is("Aquarium"))
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .build();
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(test_1)
                .log()
                .all()
                .when()
                .get("/image/l38bOK3");   // запрос к ресурсу на картинку
    }

    @DisplayName("Endpoint № 2 Upload a new image.")
    @Test
    @Order(2)
    void postImageTest() {
        ResponseSpecification test_2 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("data.size", is(273521))
                .expectBody("data.type", is("image/jpeg"))
                .expectBody("data.name", is("Gallery"))
                .expectBody("data.title", is("The best Gallery!"))
                .expectBody("status", is(200))
                .expectBody("success", is(true))
                .build();
        CURRENT_IMAGE_ID = given()
                .spec(requestSpecification) // далее добавляем параметры запроса
                .multiPart("image", getFileResource("gallery.jpg"))
                .formParam("name", "Gallery")
                .formParam("title", "The best Gallery!")
                .log()
                .all()
                .expect()
                .spec(test_2)
                .log()
                .all()
                .when()
                .post("upload")
                .body()
                .jsonPath()
                .getString("data.id");
    }  // .post указываем в методе url куда кладём, затем достаём в лог id каартинки и сохраним в CURRENT_IMAGE_ID

    @DisplayName("Endpoint № 5 Deletes an image.")
    @Test
    @Order(5)
    void deleteImageById() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .body("status", is(200))
                .log()
                .all()
                .when()
                .delete("image/" + CURRENT_IMAGE_ID);
    }

    @DisplayName("Endpoint № 4 Creates a new comment, returns the ID of the comment.")
    @Test
    @Order(4)
    void testCreateComment() {
        ResponseSpecification test_4 = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("status", is(200))
                .expectBody("success", is(true))
                .build();
        given()
                .spec(requestSpecification)
                .formParam("image_id", CURRENT_IMAGE_ID)
                .formParam("comment", "Hello world")
                .log()
                .all()
                .expect()
                .spec(test_4)
                .log()
                .all()
                .when()
                .post("comment");
    }

    @DisplayName("Endpoint № 3 Share an Album or Image to the Gallery.")
    @Test
    @Order(3)
    void testShareWithCommunity() {
        ResponseSpecification test_3 = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(2000L))
                .expectBody("status", is(200))
                .expectBody("data", is(true))
                .expectBody("success", is(true))
                .build();
        given()
                .spec(requestSpecification)
                .formParam("title", "Get this gallery to the front page")
                .formParam("topic", "Funny")
                .formParam("terms", "1")
                .formParam("tags", "funny,gallery")
                .log()
                .all()
                .expect()
                .spec(test_3)
                .log()
                .all()
                .when()
                .post("gallery/image/" + CURRENT_IMAGE_ID);
    }
}

