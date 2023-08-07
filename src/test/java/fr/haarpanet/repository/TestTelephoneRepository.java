package fr.haarpanet.repository;

import fr.haarpanet.model.Telephone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTelephoneRepository extends TestRepository {

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

    @Test
    public void saveOneTelephone() {

        TelephoneRepository telephoneRepository = new TelephoneRepository(connection);
        Telephone telephonePro = new Telephone("01 22 22 22 22");
        Telephone savedTelephone = telephoneRepository.save(telephonePro);

        assertThat(savedTelephone.getId()).isNotZero();
    }
}
