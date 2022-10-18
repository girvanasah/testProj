package apiBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONObject;

import javaUtils.ExtentReportListner;

public class UpdateUsernameWithDetails {

	static String resourceName = Constants.reqSchema + "updateUsernameWithDetails.json";

	public static JSONObject convertHashMapToJSONObject(HashMap<String, Object> details) {

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
	
	public static JSONObject updateSingleKeyInJSON(JSONObject existingPayload, String key, Object value) {
		existingPayload.put(key, value);
		ExtentReportListner.logInfo("Added key : " + key + " with value : "+value.toString());
		return existingPayload;
	}
	
	public static JSONObject updateMultiKeyInJSON(JSONObject existingPayload, HashMap<String, Object> details) {
		
		for(String key: details.keySet()) {
			existingPayload.put(key, details.get(key));
			ExtentReportListner.logInfo("Added key : " + key + " with value : "+details.get(key).toString());
	       }
		
		return existingPayload;
	}

}
