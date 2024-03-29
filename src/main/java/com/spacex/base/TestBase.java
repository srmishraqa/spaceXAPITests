package com.spacex.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public static Properties prop;

    public static void init() {
        prop = new Properties();

        try {
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
                    + "/src/main/java/com/spacex/config/config.properties");

            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
