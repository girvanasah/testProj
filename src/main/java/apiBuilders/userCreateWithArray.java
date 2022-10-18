package apiBuilders;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;



public class userCreateWithArray {
	
	static String resourceName = Constants.reqSchema + "userCreateWithArray.json";

	public static JSONArray getPayloadForMutliUser(ArrayList<HashMap<String, Object>> users) {
		JSONArray payload = new JSONArray();

		for (HashMap<String, Object> user : users) {
			payload.put(convertHashMapToJSONObject(user));
		}
		return payload;
	}

	public static JSONObject convertHashMapToJSONObject(HashMap<String, Object> details) {
		
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(resourceName)));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

        JSONArray object = new JSONArray(data);
        
        JSONObject first = object.getJSONObject(0);
		
       for(String key: details.keySet()) {
    	   first.put(key, details.get(key));
       }
        
		return first;
	}

	public static JSONArray addAdditionalUserDetails(JSONArray existingPayload, HashMap<String, Object> user) {
		existingPayload.put(convertHashMapToJSONObject(user));
		return existingPayload;
	}
	
	public static JSONArray createPayloadForSingleUser(HashMap<String, Object> details) {
		
		JSONObject user = convertHashMapToJSONObject(details);
		JSONArray payload = new JSONArray();
		payload.put(user);
		return payload;
	}
	
}
