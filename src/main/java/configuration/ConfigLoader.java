package configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {

    private static final Dotenv dotenv = Dotenv.load();

    public String getDBUrl() {
        return dotenv.get("DB_URL");
    }

    public String getDBUser(){
        return dotenv.get("DB_USER");
    }

    public String getPassword() {
        return dotenv.get("DB_PASSWORD");
    }

}
