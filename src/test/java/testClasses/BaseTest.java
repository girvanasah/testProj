package testClasses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import apiConfigs.HeaderConfigs;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import javaUtils.ExtentReportListner;
import javaUtils.PropertyReader;

@Listeners(ExtentReportListner.class)
public class BaseTest extends ExtentReportListner {

	public static final String fileSeperator = System.getProperty("file.separator");
	public static final String schemaFolder = System.getProperty("user.dir") + fileSeperator + "src" + fileSeperator
			+ "test" + fileSeperator + "resources" + fileSeperator + "testData" + fileSeperator;
	public static final String reqSchema = schemaFolder + "requestSchema";
	public static final String resSchema = schemaFolder + "responseSchema";

	HeaderConfigs header;
	@BeforeClass
	public void baseTest() {
		RestAssured.baseURI = PropertyReader.getProperty("baseURL");
		PropertyReader prop = new PropertyReader();
		header = new HeaderConfigs();
	}

}