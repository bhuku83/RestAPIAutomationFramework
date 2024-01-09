package api.endpoints;

/*
 Petstore Swagger Ui URL - https://petstore.swagger.io/
 GetUser -  https://petstore.swagger.io/v2/user/{username}
 PostUser - https://petstore.swagger.io/v2/user/createWithArray
 PutUser -  https://petstore.swagger.io/v2/user/{username}
 DeleteUser - https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {

    public static String base_URL= "https://petstore.swagger.io/v2";

    //User Module

    public static String post_URL= base_URL+ "/user";
    public static String get_URL= base_URL+ "/user/{username}";
    public static String put_URL= base_URL+ "/user/{username}";
    public static String delete_URL= base_URL+ "/user/{username}";

}
