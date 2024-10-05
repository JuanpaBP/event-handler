package configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class HibernateConfig {

    public static EntityManagerFactory setup() {
        Map<String,String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url",ConfigLoader.getDBUrl());
        properties.put("javax.persistence.jdbc.user",ConfigLoader.getDBUser());
        properties.put("javax.persistence.jdbc.password",ConfigLoader.getPassword());

        return Persistence.createEntityManagerFactory("Event_Handler", properties);
    }
}
