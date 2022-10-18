package apiBuilders;

import java.util.Random;

public class Constants {
	public static final String fileSeperator = System.getProperty("file.separator");
	public static final String schemaFolder = System.getProperty("user.dir") + fileSeperator + "src" + fileSeperator
			+ "test" + fileSeperator + "resources" + fileSeperator + "testData" + fileSeperator;
	public static final String reqSchema = schemaFolder + "requestSchema" + fileSeperator;
	public static final String resSchema = schemaFolder + "responseSchema" + fileSeperator;
	
	public static int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000);
	}
}
