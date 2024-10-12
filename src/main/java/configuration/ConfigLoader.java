package configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {

    private static final Dotenv dotenv = Dotenv.load();

    public static void loadProperties() {
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }

    public static String getDBUrl() {
        return dotenv.get("DB_URL");
    }

    public static String getDBUser(){
        return dotenv.get("DB_USER");
    }

    public static String getPassword() {
        return dotenv.get("DB_PASSWORD");
    }

}
