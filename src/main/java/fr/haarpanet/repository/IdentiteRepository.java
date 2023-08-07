package fr.haarpanet.repository;

import fr.haarpanet.model.Identite;
import fr.haarpanet.model.Personne;

import java.sql.*;

public class IdentiteRepository {

    public static final String INSERT_IDENTITE = "INSERT INTO IDENTITE (NOM_COMPLET) VALUES (?)";
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
            System.out.println(identite.getNomComplet());
            throw new RuntimeException(e);
        }
        return identite;
    }
}
