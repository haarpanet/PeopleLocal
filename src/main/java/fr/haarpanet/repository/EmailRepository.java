package fr.haarpanet.repository;

import fr.haarpanet.model.Email;

import java.sql.*;

public class EmailRepository {

    private final Connection connection;
    public static final String INSERT_EMAIL = "INSERT INTO EMAIL (VALEUR) VALUES (?)";
    private static final String FIND_BY_VALEUR = "SELECT * FROM EMAIL WHERE VALEUR LIKE CONCAT( '%', ? , '%')";
    public EmailRepository(Connection connection) {
        this.connection = connection;
    }

    public Email save(Email email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMAIL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email.getValeur());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                long idEmail = resultSet.getLong(1);
                email.setId(idEmail);
            }

        } catch(SQLException e) {
            e.getMessage();
        }
        return email;
    }

    public Email findByValeur(String email) {
        Email result = new Email();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( FIND_BY_VALEUR );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                long idEmail = resultSet.getLong("id");
                String valeur = resultSet.getString("valeur");
                result.setId(idEmail);
                result.setValeur(valeur);
            }
        } catch ( SQLException e) {
            e.getMessage();
        }
        return result;
    }
}
