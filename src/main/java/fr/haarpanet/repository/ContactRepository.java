package fr.haarpanet.repository;

import fr.haarpanet.model.Contact;

import java.sql.*;

public class ContactRepository {
    private final Connection connection;
    public static final String INSERT_CONTACT = "INSERT INTO CONTACT (EMAIL_ID) VALUES (?)";

    public ContactRepository(Connection connection) {
        this.connection = connection;
    }

    public Contact save(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, contact.getEmail().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                long idContact = resultSet.getLong(1);
                contact.setId(idContact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }
}