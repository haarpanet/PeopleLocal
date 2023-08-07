package fr.haarpanet.repository;

import fr.haarpanet.model.Identite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

public class TestIdentiteRepository extends TestRepository {

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
    public void saveOneIdentite() {
        IdentiteRepository identiteRepository = new IdentiteRepository(connection);

        String nomComplet = "Jean-Bapt Baptiste-Poquelin";
        Identite identite = new Identite(nomComplet);

        Identite savedIdentite = identiteRepository.save(identite);

        assertThat(savedIdentite.getId()).isNotZero();
    }

    @Test
    public void saveMultipleIdentites() {
        IdentiteRepository identiteRepository = new IdentiteRepository(connection);

        String nomComplet1 = "Lyz Azuelos";
        Identite identite1 = new Identite(nomComplet1);
        Identite savedIdentite1 = identiteRepository.save(identite1);

        String nomComplet2 = "Fabrice Terreau";
        Identite identite2 = new Identite(nomComplet2);
        Identite savedIdentite2 = identiteRepository.save(identite2);

        assertThat(savedIdentite1.getId()).isNotEqualTo(savedIdentite2.getId());


    }

}
