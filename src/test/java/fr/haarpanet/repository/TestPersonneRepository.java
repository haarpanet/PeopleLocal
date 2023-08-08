package fr.haarpanet.repository;
import fr.haarpanet.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Set;

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
    
    @Test
    public void saveOnePersonneWithIdentite() {
        IdentiteRepository identiteRepository = new IdentiteRepository(connection);
        PersonneRepository personneRepository = new PersonneRepository(connection);

        Identite identite = identiteRepository.findByValeur("Lyz Azuelos");
        Personne personne = new Personne(identite);
        Personne savedPersonne = personneRepository.save(personne);

        assertThat(savedPersonne.getId()).isNotZero();

    }

    @Test
    public void saveOnePersonneWithIdentiteAndContact() {
        IdentiteRepository identiteRepository = new IdentiteRepository(connection);
        ContactRepository contactRepository = new ContactRepository(connection);
        PersonneRepository personneRepository = new PersonneRepository(connection);

        Identite identite = identiteRepository.findByValeur("Fabrice Terreau");
        Set<Contact> contactsFromOneInput = contactRepository.findByValeur("mignardises@lycos.fr");
        Set<Contact> contactsFromTwoInputs = contactRepository.findByValeurs("mign", "01");

        Personne savedPersonne1 = null;
        for (Contact contact : contactsFromOneInput) {
            Personne personne = new Personne(identite, contact);
            savedPersonne1 = personneRepository.save(personne);
        }

        Personne savedPersonne2 = null;
        for (Contact contact : contactsFromTwoInputs) {
            Personne personne = new Personne(identite, contact);
            savedPersonne2 = personneRepository.save(personne);
        }
        assertThat(identite).isNotNull();
        assertThat(contactsFromOneInput).isNotEmpty();
        assertThat(contactsFromOneInput.size()).isEqualTo(contactsFromTwoInputs.size());
        if (savedPersonne1 != null && savedPersonne2 != null) {
            assertThat(savedPersonne1.getContact().getId()).isEqualTo(savedPersonne2.getContact().getId());
        }
    }

}
