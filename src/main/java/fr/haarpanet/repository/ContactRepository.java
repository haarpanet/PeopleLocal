package fr.haarpanet.repository;

import fr.haarpanet.model.Contact;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ContactRepository {
    private final Connection connection;
    public static final String INSERT_CONTACT = "INSERT INTO CONTACT (EMAIL_ID, TELEPHONE_ID) VALUES (?,?)";
    private static final String FIND_BY_EMAIL = "SELECT c.id FROM CONTACT c JOIN EMAIL e on c.email_id = e.id WHERE e.valeur LIKE CONCAT( '%', ? , '%')";
    private static final String FIND_BY_TELEPHONE= "SELECT c.id FROM CONTACT c JOIN TELEPHONE t on c.telephone_id = t.id WHERE t.valeur LIKE CONCAT ( '%', ? , '%')";

    public ContactRepository(Connection connection) {
        this.connection = connection;
    }

    public Contact save(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT, Statement.RETURN_GENERATED_KEYS);

            if ( contact.getEmail() == null ) {
                preparedStatement.setNull(1, Types.NULL);
            } else {
                preparedStatement.setLong(1, contact.getEmail().getId());
            }

            if ( contact.getTelephone() == null ) {
                preparedStatement.setNull(2, Types.NULL);
            } else {
                preparedStatement.setLong(2, contact.getTelephone().getId());
            }

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

    public Set<Contact> findByValeur(String valeur) {
        Set<Contact> results = new HashSet<>();
        try {
            launchQuery(valeur, results, FIND_BY_EMAIL);
            launchQuery(valeur, results, FIND_BY_TELEPHONE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public Set<Contact> findByValeurs(String email, String telephone) {
        StringBuilder intersectQuery = new StringBuilder();
        intersectQuery.append("SELECT c.id ")
                //
                      .append("FROM CONTACT c ")
                //
                      .append("JOIN EMAIL e ON c.email_id = e.id ")
                //
                      .append("JOIN TELEPHONE t ON c.telephone_id = t.id ")
                //
                      .append("WHERE e.valeur LIKE CONCAT('%', ? , '%') ")
                //
                      .append("AND t.valeur LIKE CONCAT('%', ? ,'%') ");

        Set<Contact> results = new HashSet<>();
        try {
            launchQuery(email, telephone, results, intersectQuery.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    private void launchQuery(String valeur, Set<Contact> results, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        setParameter(valeur, 1, preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            long idContact = resultSet.getLong("id");
            Contact newContact = new Contact();
            newContact.setId(idContact);
            results.add(newContact);
        }
    }

    private void launchQuery(String valeur1, String valeur2, Set<Contact> results, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        setParameter(valeur1, 1, preparedStatement);
        setParameter(valeur2, 2, preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            long idContact = resultSet.getLong("id");
            Contact newContact = new Contact();
            newContact.setId(idContact);
            results.add(newContact);
        }
    }

    private void setParameter(String valeur, int index, PreparedStatement preparedStatement) throws SQLException {
        if ( valeur == null || valeur.isBlank() ) {
            preparedStatement.setNull(index, Types.NULL);
        } else {
            preparedStatement.setString(index, valeur);
        }
    }


}
