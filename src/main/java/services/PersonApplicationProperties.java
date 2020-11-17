package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PersonApplicationProperties {

    private static final PersonApplicationProperties INSTANCE = new PersonApplicationProperties();

    private final Properties applicationProperties = new Properties();

    private PersonApplicationProperties() {
        loadPropertyFile();
    }

    public static PersonApplicationProperties getInstance() {
        return INSTANCE;
    }

    public String getValueOf(String key) {
        return applicationProperties.getProperty(key);
    }

    private void loadPropertyFile() {
        String fileName = "application.properties";
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            applicationProperties.load(resourceAsStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
