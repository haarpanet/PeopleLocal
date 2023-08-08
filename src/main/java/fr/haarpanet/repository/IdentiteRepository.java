package fr.haarpanet.repository;

import fr.haarpanet.model.Identite;

import java.sql.*;

public class IdentiteRepository {

    public static final String INSERT_IDENTITE = "INSERT INTO IDENTITE (NOM_COMPLET) VALUES (?)";
    private static final String FIND_BY_VALEUR = "SELECT * FROM IDENTITE WHERE NOM_COMPLET LIKE CONCAT( '%', ? , '%')";
    private final Connection connection;

    public IdentiteRepository(Connection connection) {
        this.connection = connection;
    }

    public Identite save(Identite identite) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IDENTITE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1, identite.getNomComplet());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                long newId = resultSet.getLong(1);
                identite.setId(newId);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return identite;
    }

    public Identite findByValeur(String valeur) {
        Identite result = new Identite();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_VALEUR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1, valeur);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long newId = resultSet.getLong("id");
                result.setId(newId);
                result.setNomComplet(valeur);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
