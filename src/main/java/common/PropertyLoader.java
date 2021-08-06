package common;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROP_FILE = "/application.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            System.out.println("Unable to load properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String loadProperty(String name) {
        String value = properties.getProperty(name);
        if (value == null) {
            throw new RuntimeException("Unable to find property: " + name);
        }
        return value;
    }
}
