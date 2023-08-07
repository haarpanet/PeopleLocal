package fr.haarpanet.repository;

import fr.haarpanet.model.Telephone;

import java.sql.*;

public class TelephoneRepository {
    private final Connection connection;
    public static final String INSERT_TELEPHONE = "INSERT INTO TELEPHONE (VALEUR) VALUES (?)";

    public TelephoneRepository(Connection connection) {
        this.connection = connection;
    }

    public Telephone save(Telephone telephone) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TELEPHONE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, telephone.getValeur());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                long idTelephone = resultSet.getLong(1);
                telephone.setId(idTelephone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return telephone;
    }
}
