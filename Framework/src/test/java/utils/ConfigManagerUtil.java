package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerUtil {

    public static Properties readFile(String fileName) {
        Properties property = new Properties();
        try(FileInputStream fis = new FileInputStream(fileName)) {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
