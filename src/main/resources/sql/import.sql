
-- Insertion des données dans la table EMAIL
INSERT INTO EMAIL (id, valeur) VALUES
    (1, 'email1@example.com'),
    (2, 'email2@example.com'),
    (3, 'email3@example.com');

-- Insertion des données dans la table TELEPHONE
INSERT INTO TELEPHONE (id, valeur) VALUES
    (1, '1234567890'),
    (2, '9876543210'),
    (3, '5555555555');

-- Insertion des données dans la table IDENTITE
INSERT INTO IDENTITE (id, nom_complet) VALUES
    (1, 'Jean Dupont'),
    (2, 'Marie Martin'),
    (3, 'Pierre Lambert');

-- Insertion des données dans la table CONTACT
INSERT INTO CONTACT (id, email_id, telephone_id) VALUES
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 3);

-- Insertion des données dans la table PERSONNE
INSERT INTO PERSONNE (id, identite_id, contact_id) VALUES
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 3);

