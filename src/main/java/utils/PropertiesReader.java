package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader instance = null;
    private Properties properties;

    private PropertiesReader() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/resources/GlobalData.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesReader getInstance(){
        if (instance==null){
            instance = new PropertiesReader();
        }
        return instance;
    }

    public String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }
}
