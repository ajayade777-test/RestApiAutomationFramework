package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoint.userEndpoint;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {
	
	@Test(priority=1, dataProvider= "AllData", dataProviderClass = DataProviders.class)
	public void testCreateUser(String userId,  String UserName, String fname, String lname, String email, String pwd, String phone) {
        user userPayload = new user();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		//userPayload.setUserStatus(0);
		
		
		Response response= userEndpoint.CreateUser(userPayload);
		
		
		//log response
		System.out.println("Creating User Details   :\n");
		response.then().log().all();
		
		
		//validate status code
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}


	@Test(priority=2, dataProvider= "userNamesData", dataProviderClass = DataProviders.class)
	public void testGetUser(String username) {
		
		Response response= userEndpoint.GetUser(username);
		
		//log response
		System.out.println(" \n Getting/Fetching User Details :\n");
		response.then().log().all();
		
		//validate status code
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3, dataProvider= "userNamesData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username) {
		
		Response response= userEndpoint.DeleteUser(username);
		
		//log response
		System.out.println(" \n Deleting User Details   :\n");
		response.then().log().all();
		
		//validate status code
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
