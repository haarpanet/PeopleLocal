package fr.haarpanet.repository;

import fr.haarpanet.model.Contact;
import fr.haarpanet.model.Email;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class TestContactRepository extends TestRepository {

    private EmailRepository emailRepository;

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
    public void saveContactWithEmail() {
        emailRepository = new EmailRepository(connection);
        ContactRepository contactRepository = new ContactRepository(connection);
        Email email = emailRepository.findByValeur("jean-bapt@voila.fr");
        Contact contact = new Contact(email);
        Contact savedContact = contactRepository.save(contact);
    }
}
