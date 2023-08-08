package fr.haarpanet.repository;

import fr.haarpanet.model.Telephone;
import java.sql.*;

public class TelephoneRepository {
    private static final String FIND_BY_VALEUR = "SELECT * FROM TELEPHONE WHERE VALEUR LIKE CONCAT( '%', ? , '%')";
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

    public Telephone findByValeur(String telephone) {
        Telephone result = new Telephone();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement( FIND_BY_VALEUR );
            preparedStatement.setString(1, telephone);
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
