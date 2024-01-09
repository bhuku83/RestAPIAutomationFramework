package api.endpoints;

//Created to perform CRUD operation - Created, Retrieve, update delete operation

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {

    public static Response createUsers(User Payload){

        Response response =
                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(Payload)
                .when()
                    .post(Routes.post_URL);

        return response;

    }

    public static Response readUsers(String userName){

        System.out.println(Routes.get_URL);

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("username",userName )
                .when()
                        .get(Routes.get_URL);

        return response;

    }

    public static Response updateUsers(String userName, User Payload){

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(Payload)
                        .pathParams("username", userName)
                .when()
                        .put(Routes.put_URL);

        return response;

    }

    public static Response deleteUsers(String userName){

        Response response =
                given()
                        .pathParams("username",userName )
                        .when()
                .delete(Routes.delete_URL );

        return response;

    }


}
