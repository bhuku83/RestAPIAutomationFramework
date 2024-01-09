package api.tests;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testDataProvider {

    @Test(priority = 1, dataProvider="Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone){

        Faker faker =new Faker();
        User userPayLoad = new User();
        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setUsername(UserName);
        userPayLoad.setFirstName(FirstName);
        userPayLoad.setLastName(LastName);
        userPayLoad.setEmail(Email);
        userPayLoad.setPassword(Password);
        userPayLoad.setPhone(Phone);

        Response response= UserEndPoints.createUsers(userPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(dependsOnMethods = {"testPostUser"}, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String UserName)
    {
        Response response = UserEndPoints.deleteUsers(UserName);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
