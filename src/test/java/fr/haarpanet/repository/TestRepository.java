package fr.haarpanet.repository;

import java.sql.Connection;

public abstract class TestRepository {

    public static final String JDBC_H2_PEOPLELOCAL = "jdbc:postgresql://localhost:5432/persons?currentSchema=public&user=postgres&password=Ausy2020!";
    Connection connection;

}
