package testClasses;

import java.io.File;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import apiBuilders.CreateMultiPets;
import apiConfigs.HeaderConfigs;
import apiConfigs.apiPath;
import apiVerifications.APIVerification;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import javaUtils.PropertyReader;

public class PetStoreTest extends BaseTest {

	JSONObject payload;

	@Test
	public void createMultiplePets() {

		HashMap<String, Object> petData = new HashMap<String, Object>();

		petData.put("id", Integer.parseInt(PropertyReader.getProperty("pet_id")));

		HashMap<String, Object> category = new HashMap<String, Object>();
		category.put("id", Integer.parseInt(PropertyReader.getProperty("category_id")));
		category.put("name", PropertyReader.getProperty("category_name"));

		petData.put("category", new JSONObject(category));

		petData.put("name", PropertyReader.getProperty("name"));

		String photoURL[] = PropertyReader.getProperty("photoUrls").split(",");
		petData.put("photoUrls", photoURL);

		HashMap<String, Object> tags = new HashMap<String, Object>();
		tags.put("id", Integer.parseInt(PropertyReader.getProperty("tags_id")));
		tags.put("name", PropertyReader.getProperty("tags_id"));
		JSONArray JSONArray_tags = new JSONArray();
		JSONArray_tags.put(new JSONObject(tags));

		petData.put("tags", JSONArray_tags);

		petData.put("status", PropertyReader.getProperty("status"));

		payload = CreateMultiPets.createPayloadWithGivenValues(petData);

		Response res = RestAssured.given().headers(header.defaultHeaders()).body(payload)
				.post(apiPath.CREATE_MULTI_PETS);

		APIVerification.responseCodeValiddation(res, 201);

	}

	@Test(dependsOnMethods = "createMultiplePets")
	public void updatePetStatus() {

		payload = CreateMultiPets.updateSingleKeyInPayload(payload, "status", PropertyReader.getProperty("newStatus"));

		Response res = RestAssured.given().headers(header.defaultHeaders()).body(payload)
				.put(apiPath.UPDATE_PET_DETAILS);
		APIVerification.responseCodeValiddation(res, 201);
	}

	@Test(dependsOnMethods = "updatePetStatus")
	public void getPetDetails() {

		File file = new File(resSchema + fileSeperator + "getPetByStatus.json");

		Response res = RestAssured.given().headers(header.defaultHeaders())
				.queryParam("status", payload.getString("status")).get(apiPath.GET_PET_BY_STATUS);
		
		RestAssured.given().headers(header.defaultHeaders()).queryParam("status", payload.getString("status"))
				.get(apiPath.GET_PET_BY_STATUS).then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));

		Assert.assertTrue(APIVerification.responseCodeValiddation(res, 200));
	}
}
