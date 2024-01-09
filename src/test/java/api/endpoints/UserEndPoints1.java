package api.endpoints;

//Created to perform CRUD operation - Created, Retrieve, update delete operation

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints1 {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }


    public static Response createUsers(User Payload){

        String post_URL = getURL().getString("post_url");
        Response response =
                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(Payload)
                .when()
                    .post(post_URL);

        return response;

    }

    public static Response readUsers(String userName){

        String get_URL = getURL().getString("get_url");
        System.out.println(Routes.get_URL);

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("username",userName )
                .when()
                        .get(get_URL);

        return response;

    }

    public static Response updateUsers(String userName, User Payload){

        String put_URL=getURL().getString("update_url");
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(Payload)
                        .pathParams("username", userName)
                .when()
                        .put(put_URL);

        return response;

    }

    public static Response deleteUsers(String userName){
        String delete_URL=getURL().getString("delete_url");
        Response response =
                given()
                        .pathParams("username",userName )
                        .when()
                .delete(Routes.delete_URL );

        return response;

    }


}
