package fr.haarpanet.repository;

import fr.haarpanet.model.Contact;
import fr.haarpanet.model.Identite;
import fr.haarpanet.model.Personne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static java.sql.DriverManager.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestPersonneRepository extends TestRepository {

    @BeforeEach
    public void setUp() throws SQLException {
        String standardUrl = JDBC_H2_PEOPLELOCAL;
        connection = getConnection(standardUrl);

    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }


}
