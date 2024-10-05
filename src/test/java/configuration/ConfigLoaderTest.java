package configuration;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConfigLoaderTest {

    private ConfigLoader configLoader = new ConfigLoader();

    @Test
    public void shouldReturnDBURLWhenGetDBURLIsCalled() {
        String dbUrl = configLoader.getDBUrl();
        assertFalse(dbUrl.isEmpty());
    }

    @Test
    public void shouldReturnDBUserWhenGetDBUserIsCalled() {
        String dbUser = configLoader.getDBUser();
        assertFalse(dbUser.isEmpty());
    }

    @Test
    public void shouldReturnDBPasswordWhenGetDB() {
        String
    }
}