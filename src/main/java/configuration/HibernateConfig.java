package configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class HibernateConfig {

    public static EntityManagerFactory setup() {
        System.setProperty("db.type", "mariadb"); //TODO: Esto NO SACAR. TODO ESTO ES POR QUE YO ESTOY USANDO MARIADB



        Map<String,String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url",ConfigLoader.getDBUrl());
        properties.put("javax.persistence.jdbc.user",ConfigLoader.getDBUser());
        properties.put("javax.persistence.jdbc.password",ConfigLoader.getPassword());


        //***********************
        String dbType = System.getProperty("db.type", "mysql"); // Default a MySQL
        System.out.println("Database Type: " + dbType); // Esto debería imprimir "mariadb" o "mysql"
        String dialect;
        if ("mariadb".equalsIgnoreCase(dbType)) {
            dialect = "org.hibernate.dialect.MariaDBDialect";
        } else {
            dialect = "org.hibernate.dialect.MySQLDialect";
        }
        properties.put("hibernate.dialect", dialect);
        System.out.println("Hibernate Dialect: " + properties.get("hibernate.dialect")); // Esto debería imprimir el dialecto correcto
        //TODO: Esto NO SACAR. TODO ESTO ES POR QUE YO ESTOY USANDO MARIADB, Y ustedes no. Basicamente es: Si estas usando SQl, usa dialecto de SQL, si estas usando MariaDB, usa dialecto
        //de mariaDB

        //******************


        System.out.println(properties.get("javax.persistence.jdbc.url"));
        System.out.println(properties.get("javax.persistence.jdbc.user"));
        System.out.println(properties.get("javax.persistence.jdbc.password"));
        System.out.println(properties.get("hibernate.dialect"));

        return Persistence.createEntityManagerFactory("MyPersistenceUnit", properties);
    }



}
