package fr.haarpanet.repository;

import fr.haarpanet.model.Identite;
import fr.haarpanet.model.Personne;

import java.sql.*;

public class PersonneRepository {




    public static final String INSERT_PERSONNE= "INSERT INTO PERSONNE (IDENTITE_ID, CONTACT_ID) VALUES (?)";
    private final Connection connection;

    public PersonneRepository(Connection connection) {
        this.connection = connection;
    }

    public Personne save(Personne personne) {
        long idIdentite = -1L;
        long idEmail = -1L;
        long idTelephone = -1L;
        long idContact = -1L;
        long idPersonne = -1L;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONNE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, personne.getIdentite().getNomComplet());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
;
            while (resultSet.next()) {
               idIdentite= resultSet.getLong(1);
               personne.getIdentite().setId(idIdentite);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personne;
    }
}
