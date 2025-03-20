import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * методы работы с Properties
 */

public class DataProperties {
    public static Properties property;

    public DataProperties() {}

    public String getDataProperties(String nameFile, String nameKeyValue) {
        property = new Properties();
        InputStream file = ClassLoader.getSystemResourceAsStream(nameFile);
        try {
            property.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return property.getProperty(nameKeyValue);
    }
}
