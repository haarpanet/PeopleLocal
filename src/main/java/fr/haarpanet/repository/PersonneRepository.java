package fr.haarpanet.repository;

import fr.haarpanet.model.Personne;

import java.sql.*;

public class PersonneRepository {




    public static final String INSERT_PERSONNE= "INSERT INTO PERSONNE (IDENTITE_ID, CONTACT_ID) VALUES (?, ?)";
    private final Connection connection;

    public PersonneRepository(Connection connection) {
        this.connection = connection;
    }

    public Personne save(Personne personne) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONNE, Statement.RETURN_GENERATED_KEYS);

            if ( personne.getIdentite() == null ) {
                preparedStatement.setNull(1, Types.NULL);
            } else {
                preparedStatement.setLong(1, personne.getIdentite().getId());
            }

            if ( personne.getContact() == null ) {
                preparedStatement.setNull(2, Types.NULL);
            } else {
                preparedStatement.setLong(2, personne.getContact().getId());
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                long idContact = resultSet.getLong(1);
                personne.setId(idContact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personne;

    }
}
