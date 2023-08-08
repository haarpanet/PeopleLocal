-- Table EMAIL
CREATE TABLE EMAIL (
    id INT PRIMARY KEY,
    valeur VARCHAR(255) NOT NULL UNIQUE
);

-- Table TELEPHONE
CREATE TABLE TELEPHONE (
    id INT PRIMARY KEY,
    valeur VARCHAR(20) NOT NULL UNIQUE
);

-- Table IDENTITE
CREATE TABLE IDENTITE (
    id INT PRIMARY KEY,
    nom_complet VARCHAR(255) NOT NULL UNIQUE
);

-- Table CONTACT
CREATE TABLE CONTACT (
    id INT PRIMARY KEY,
    email_id INT,
    telephone_id INT,
    identite_id INT,
    FOREIGN KEY (email_id) REFERENCES EMAIL(id),
    FOREIGN KEY (telephone_id) REFERENCES TELEPHONE(id),
    FOREIGN KEY (identite_id) REFERENCES IDENTITE(id),
    UNIQUE (email_id, telephone_id)
);

-- Table PERSONNE
CREATE TABLE PERSONNE (
    id INT PRIMARY KEY,
    identite_id INT NOT NULL,
    contact_id INT,
    FOREIGN KEY (identite_id) REFERENCES IDENTITE(id),
    FOREIGN KEY (contact_id) REFERENCES CONTACT(id)
);

