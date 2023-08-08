package fr.haarpanet.repository;

import fr.haarpanet.model.Contact;
import fr.haarpanet.model.Email;
import fr.haarpanet.model.Telephone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

public class TestContactRepository extends TestRepository {

    private EmailRepository emailRepository;
    private TelephoneRepository telephoneRepository;

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
        assertThat(savedContact.getId()).isNotZero();
    }

    @Test
    public void saveContactWithTelephone() {
        telephoneRepository = new TelephoneRepository(connection);
        ContactRepository contactRepository = new ContactRepository(connection);
        Telephone telephone = telephoneRepository.findByValeur("01 22 22 22 22");
        Contact contact = new Contact(telephone);
        Contact savedContact = contactRepository.save(contact);
        assertThat(savedContact.getId()).isNotZero();
    }

    @Test
    public void saveContactWithTelephoneAndEmail() {
        telephoneRepository = new TelephoneRepository(connection);
        emailRepository = new EmailRepository(connection);
        ContactRepository contactRepository = new ContactRepository(connection);

        Email email = emailRepository.findByValeur("mignardises@lycos.fr");
        Telephone telephone = telephoneRepository.findByValeur("01 22 22 22 22");
        Contact contact = new Contact(email,telephone);
        Contact savedContact = contactRepository.save(contact);
        assertThat(savedContact.getId()).isNotZero();
    }
}
