package api.test;

import java.util.HashMap;

//
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;

	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1, enabled = false)
	public void testPostUser() {
		logger.info("******************* Creating User *************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		System.out.println("Response  " + response + "End Here");

		Assert.assertEquals(response.getStatusCode(), 200);
		//Assert.assertEquals(response.get, 200);
		System.out.println("<>>>>>" + response.getHeaders() + "<<<>>>><<<>");
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		//System.out.println("<>>Co>>>" + response.getCookies() + "<<<>>>Co><<<>");
		// Assert.assertEqauls(response.header("Content-Type","application/json"));
		logger.info("******************* User is created *************");
	}

	@Test(priority = 2, enabled = true)
	public void testGetUserByName() {
		logger.info("******************* Reading User *************");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		System.out.println("Response  " + response + "End Here");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************* User info displayed *************");
	}

	@Test(priority = 3, enabled = false)
	public void testupdateUserByName() {
		logger.info("******************* Updating User *************");
		// update data using payload changes
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().body().statusCode(200); // this is chai asertion which comes with rest assured

		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking data after update

		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("******************* User is updated *************");
	}

	@Test(priority = 4, enabled = false)
	public void testdeleteUserByName() {
		logger.info("******************* Deleting User *************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************* User is deleted *************");
	}

}
