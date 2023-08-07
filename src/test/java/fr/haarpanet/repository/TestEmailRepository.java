package fr.haarpanet.repository;

import fr.haarpanet.model.Email;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

public class TestEmailRepository extends TestRepository {

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
    public void saveOneEmail() {

        EmailRepository emailRepository = new EmailRepository(connection);
        Email email = new Email("jean-bapt@voila.fr");
        Email savedEmail = emailRepository.save(email);

        assertThat(savedEmail.getId()).isNotZero();
    }

    @Test
    public void saveMultipleEmails() {

        EmailRepository emailRepository = new EmailRepository(connection);
        Email email1 = new Email("mignardises@lycos.fr");
        Email savedEmail1 = emailRepository.save(email1);

        Email email2 = new Email("fterreau@yahoo.fr");
        Email savedEmail2 = emailRepository.save(email2);

        assertThat(savedEmail1.getId()).isNotEqualTo(savedEmail2.getId());

    }
}
