package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.userEndpoint;
import api.payload.user;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	user userPayload;
	
	//obtain logger
	 private static final Logger logger = LogManager.getLogger("RestAssuredAutomationFramework");
	
	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new user();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(0);
		
		System.out.println("request body : " + userPayload);
		
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		
		Response response= userEndpoint.CreateUser(userPayload);
		
		//log response
		System.out.println("Creating User Details   :\n");
		response.then().log().all();
		
		
		//validate status code
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Logger
		logger.info("Create User executed");
	}

	

	@Test(priority=2)
	public void testGetUser() {
		
		Response response= userEndpoint.GetUser(this.userPayload.getUsername());
		
		//log response
		System.out.println(" \n Getting/Fetching User Details :\n");
		response.then().log().all();
		
		//validate status code
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//Logger
		logger.info("Get User executed");
	}
	
	

	@Test(priority=3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response= userEndpoint.UpdateUser(this.userPayload.getUsername(),userPayload);
		
		//log response
		response.then().log().all();
		
		
		//validate status code
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//read / fetch user data to check name and email Updated or not
		Response responseAfterUpdate= userEndpoint.GetUser(this.userPayload.getUsername());
		System.out.println(" \n After Updating User Details   :\n");
		responseAfterUpdate.then().log().all();
		
		//Logger
		logger.info("Update User executed");
	}
	

	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response= userEndpoint.DeleteUser(this.userPayload.getUsername());
		
		//log response
		System.out.println(" \n Deleting User Details   :\n");
		response.then().log().all();
		
		//validate status code
		Assert.assertEquals(response.getStatusCode(), 200);
		//Logger
		logger.info("Delete User executed");
	}
}
