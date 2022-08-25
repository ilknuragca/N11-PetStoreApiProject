package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static{
        //path of the config file
        String path="configuration.properties";

        try{
            //opening file
            FileInputStream file= new FileInputStream(path);
            //loading file
            properties= new Properties();
            properties.load(file);
            file.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //accepts key and return value
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
