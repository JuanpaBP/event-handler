package configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {

    private static final Dotenv dotenv = Dotenv.load();

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
