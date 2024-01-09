package api.tests;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;


public class UserTests {
    Faker faker;
    User userPayload;
    Logger logger;

    @BeforeClass
    public void setupData(){
        faker= new Faker();
        userPayload = new User();


        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logger = (Logger) LogManager.getLogger(this.getClass());
        logger= LogManager.getLogger(this.getClass());

    }

    @Test
    public void testPostUser(){

        logger.info("******************* Creating Test user using post API call ************************");
        Response response=UserEndPoints.createUsers(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******************* Test user Created using post API call ************************");
    }

    @Test(dependsOnMethods = {"testPostUser"})
    public void testGetUserByName(){

        logger.info("******************* Read Test user using get API call ************************");

        Response response= UserEndPoints.readUsers(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******************* Read Test user is successful ************************");
    }

    @Test(dependsOnMethods = {"testGetUserByName"})
    public void testUserUpdateUserByFirstName(){

        logger.info("******************* updating Test user using put API call ************************");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints.updateUsers(this.userPayload.getUsername(),userPayload);

        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        Response responseAfterUpdate= UserEndPoints.readUsers(this.userPayload.getUsername());
        responseAfterUpdate.then().log().body();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

        logger.info("******************* Update Test user using put API call ************************");
    }
    @Test(dependsOnMethods = {"testUserUpdateUserByFirstName"})
    public void testDeleteUserByUserName(){

        logger.info("******************* Delete Test user using get API call ************************");

        Response response = UserEndPoints.deleteUsers(this.userPayload.getUsername());

        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("******************* Delete Test user is successful ************************");

    }

}
