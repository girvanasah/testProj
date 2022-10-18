package javaUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	static Properties properties;
	
	private static void loadProperty() throws IOException {
		String env = "env";
        if (properties == null) {
            properties = new Properties();
            properties.load(
                    new FileInputStream(
                            System.getProperty("user.dir") +
                                    "/src/test/resources/testData/properties/"+ env +".properties"
                    )
            );
        }
    }

    public static String getProperty(String property) {
        try {
            loadProperty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties.getProperty(property);
    }
    
}
