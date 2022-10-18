package testClasses;

import java.io.File;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.*;

import apiBuilders.Constants;
import apiBuilders.UpdateUsernameWithDetails;
import apiBuilders.userCreateWithArray;
import apiConfigs.HeaderConfigs;
import apiConfigs.apiPath;
import apiVerifications.APIVerification;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import javaUtils.PropertyReader;

public class UserDetailsTest extends BaseTest {

	@Test
	public void createMultiUserWithArray() {
		HashMap<String, Object> user = new HashMap<String, Object>();

		user.put("id", Constants.randomNumber());
		user.put("username", PropertyReader.getProperty("username"));
		user.put("firstName", PropertyReader.getProperty("firstName"));
		user.put("lastName", PropertyReader.getProperty("lastName"));
		user.put("email", PropertyReader.getProperty("email"));
		user.put("password", PropertyReader.getProperty("password"));
		user.put("phone", PropertyReader.getProperty("phone"));
		user.put("userStatus", Integer.parseInt(PropertyReader.getProperty("userStatus")));

		HeaderConfigs header = new HeaderConfigs();

		Response res = RestAssured.given().headers(header.defaultHeaders())
				.body(userCreateWithArray.createPayloadForSingleUser(user)).post(apiPath.CREATE_MULTI_USER_ARRAY);

		Assert.assertTrue(APIVerification.responseCodeValiddation(res, 201));
	}

	@Test(dependsOnMethods = "createMultiUserWithArray")
	public void updateUserDetails() {

		HashMap<String, Object> user = new HashMap<String, Object>();

		user.put("id", Constants.randomNumber());
		user.put("username", PropertyReader.getProperty("newUsername"));
		user.put("firstName", PropertyReader.getProperty("newFirstName"));
		user.put("lastName", PropertyReader.getProperty("newLastName"));
		user.put("email", PropertyReader.getProperty("newEmail"));
		user.put("password", PropertyReader.getProperty("newPassword"));
		user.put("phone", PropertyReader.getProperty("newPhone"));
		user.put("userStatus", Integer.parseInt(PropertyReader.getProperty("newUserStatus")));

		HeaderConfigs header = new HeaderConfigs();

		String oldUsername = PropertyReader.getProperty("username");

		Response res = RestAssured.given().headers(header.defaultHeaders())
				.body(UpdateUsernameWithDetails.convertHashMapToJSONObject(user))
				.put(String.format(apiPath.UPDATE_USER_NAME_AND_DETAILS, oldUsername));

		Assert.assertTrue(APIVerification.responseCodeValiddation(res, 201));
	}

	@Test(dependsOnMethods = "updateUserDetails")
	public void getUserDetails() {
		HeaderConfigs header = new HeaderConfigs();
		String userName = PropertyReader.getProperty("newUsername");

		File file = new File(resSchema + fileSeperator + "getUserDetails.json");

		Response res = RestAssured.given().headers(header.defaultHeaders())
				.get(String.format(apiPath.GET_USER, userName));
		
		RestAssured.given().headers(header.defaultHeaders()).get(String.format(apiPath.GET_USER, userName)).then()
				.assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));

		Assert.assertTrue(APIVerification.responseCodeValiddation(res, 200));

	}

}
