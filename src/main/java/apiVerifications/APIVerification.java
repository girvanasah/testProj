package apiVerifications;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import javaUtils.ExtentReportListner;

public class APIVerification extends ExtentReportListner {

	public static boolean responseCodeValiddation(Response response, int statusCode) {

		try {
			Assert.assertEquals(statusCode, response.getStatusCode());
			test.log(LogStatus.PASS,
					"Successfully validdated status code, status code is :: " + response.getStatusCode());
			return true;
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			test.log(LogStatus.FAIL,
					"Expected status code is :: " + statusCode + " , insted of getting :: " + response.getStatusCode());
			return false;
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			return false;
		}
	}

	public static void responseKeyValidationfromArray(Response response, String key) {
		try {
			JSONArray array = new JSONArray(response.getBody().asString());
			for(int i=0; i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				test.log(LogStatus.PASS, "Validated value is  " + obj.get(key));
				
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}
	
	
	public static void responseKeyValidationFromJsonObject(Response response, String key) {
		try {
			JSONObject json = new JSONObject(response.getBody().asString());
			if(json.has(key) && json.get(key)!= null) {
				test.log(LogStatus.PASS, "Sucessfully validated value of " + key + " It is " + json.get(key));
			}else {
				test.log(LogStatus.FAIL,"Key is not availble");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}
	
}