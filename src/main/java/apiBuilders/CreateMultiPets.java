package apiBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONObject;

import javaUtils.ExtentReportListner;

public class CreateMultiPets {
	
	static String resourceName = Constants.reqSchema + "createMultiPets.json";
	
	public static JSONObject createPayloadWithGivenValues(HashMap<String, Object> details) {

		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(resourceName)));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JSONObject object = new JSONObject(data);
		
		for(String key: details.keySet()) {
			object.put(key, details.get(key));
	       }
		ExtentReportListner.logInfo("Created new JSON payload : "+ object.toString());
		return object;
	}
	
	public static JSONObject updateSingleKeyInPayload(JSONObject existingPayload, String key, Object value) {
		existingPayload.put(key, value);
		ExtentReportListner.logInfo("Added key : " + key + " with value : "+value.toString());
		return existingPayload;
	}
}
