//package api.test;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.github.javafaker.Faker;
//
//import api.endpoints.UserEndPoints;
//import api.payload.User;
//import io.restassured.response.Response;
//
//public class PetTests {
//	
//	Faker faker;
//	User userPayload;
//	
//	public Logger logger;
//	
//	@BeforeClass
//	public void setup()
//	{
//		faker = new Faker();
//		userPayload=new User();
//		
//		userPayload.setId(faker.idNumber().hashCode());
//		userPayload.setUsername(faker.name().username());
//		userPayload.setFirstName(faker.name().firstName());
//		userPayload.setLastName(faker.name().lastName());
//		userPayload.setEmail(faker.internet().safeEmailAddress());
//		userPayload.setPassword(faker.internet().password(5, 10));
//		userPayload.setPhone(faker.phoneNumber().cellPhone());
//		
//		//logs
//		logger = LogManager.getLogger(this.getClass());
//	}
//	
//	@Test(priority=1)
//	public void testPostUser()
//	{
//		logger.info("******************* Creating User *************");
//		Response response = UserEndPoints.createUser(userPayload);
//		response.then().log().all();
//		
//		Assert.assertEquals(response.getStatusCode(),200);
//		//Assert.assertEqauls(response.header("Content-Type","application/json"));
//		logger.info("******************* User is created *************");
//	}
//}
