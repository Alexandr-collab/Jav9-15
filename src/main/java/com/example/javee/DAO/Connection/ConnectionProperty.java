package com.example.javee.DAO.Connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperty {
    public static final String CONFIG_NAME = "application.properties";
    // Свойства конфигурации
    public static final Properties PROPERTY_CONFIG = new Properties();
    public ConnectionProperty() throws FileNotFoundException,
            IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        PROPERTY_CONFIG.load(classLoader.getResourceAsStream("config/" +
                CONFIG_NAME));
    }
    // Получить значение параметра из конфигурации по имени свойства
    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}
