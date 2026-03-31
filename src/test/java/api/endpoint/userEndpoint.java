package api.endpoint;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import api.payload.user;
import io.restassured.response.Response;

public class userEndpoint {
	
	public static Response CreateUser (user payload) {
		Response response = given()
	    .accept(ContentType.JSON)
	    .contentType(ContentType.JSON)
	    .body(payload)
	    
	    .when()
	    .post(Routes.post_url);
	    
		return response; 
				
	}

	public static Response GetUser (String userName) {
		Response response = given()
	    .accept(ContentType.JSON)
	    .pathParam("username", userName)
	    
	    .when()
	    .get(Routes.get_url);
	    
		return response; 
				
	}
	
	public static Response UpdateUser (String userName, user payload) {
		Response response = given()
	    .accept(ContentType.JSON)
	    .contentType(ContentType.JSON)
	    .pathParam("username", userName)
	    .body(payload)
	    
	    .when()
	    .put(Routes.put_url);
	    
		return response; 
				
	}
	
	
	public static Response DeleteUser (String userName) {
		Response response = given()
	    .accept(ContentType.JSON)
	    .pathParam("username", userName)
	    
	    .when()
	    .delete(Routes.delete_url);
	    
		return response; 
				
	}
	
	
}
