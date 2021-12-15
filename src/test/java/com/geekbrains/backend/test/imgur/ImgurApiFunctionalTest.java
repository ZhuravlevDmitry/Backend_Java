package com.geekbrains.backend.test.imgur;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@DisplayName("Tests RA")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiFunctionalTest extends ImgurApiAbstractTest {

    private static String CURRENT_IMAGE_ID;

    @DisplayName("Endpoint № 1 Get information about an image.")
    @Test
    @Order(1) //авторизовались,
        // конфигурим запрос - записываем лог
        // выполняем проверку в боди (значение ключ),
        // конфигурим ответ записываем лог,
    void getAccountBase() {
        String userName = "mityidizel";
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .body("data.id", is("l38bOK3"))   // проверка
                .log()
                .all()
                .when()
                .get("/image/l38bOK3");   // запрос к ресурсу на картинку
    }

    @DisplayName("Endpoint № 2 Upload a new image.")
    @Test
    @Order(2)
    void postImageTest() {
        CURRENT_IMAGE_ID = given()
                .spec(requestSpecification) // далее добавляем параметры запроса
                .multiPart("image", getFileResource("gallery.jpg"))
                .formParam("name", "Gallery")
                .formParam("title", "The best Gallery!")
                .log()
                .all()
                .expect()
                .body("data.size", is(273521)) // проверки через матчеры - тестирующее на совпадение с условием
                .body("data.type", is("image/jpeg"))
                .body("data.name", is("Gallery"))
                .body("data.title", is("The best Gallery!"))
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
    @Order(4) //"DL8vpDH"
    void testCreateComment() {
        given()
                .spec(requestSpecification)
                .formParam("image_id", CURRENT_IMAGE_ID)
                .formParam("comment", "Hello world")
                .log()
                .all()
                .expect()
                .body("success", is(true))
                .body("status", is(200))
                .log()
                .all()
                .when()
                .post("comment");
    }

    @DisplayName("Endpoint № 3 Share an Album or Image to the Gallery.")
    @Test
    @Order(3)
    void testShareWithCommunity() {
        given()
                .spec(requestSpecification)
                .formParam("title", "Get this gallery to the front page")
                .formParam("topic", "Funny")
                .formParam("terms", "1")
                .formParam("tags", "funny,gallery")
                .log()
                .all()
                .expect()
                .body("success", is(true))
                .body("status", is(200))
                .log()
                .all()
                .when()
                .post("gallery/image/" + CURRENT_IMAGE_ID);
    }
}

