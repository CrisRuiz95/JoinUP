CREATE DATABASE IF NOT EXISTS eventos_db;
USE eventos_db;

CREATE TABLE Usuarios (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    d_nombre VARCHAR(45),
    d_ap1 VARCHAR(45),
    d_ap2 VARCHAR(45),
    d_email VARCHAR(45),
    d_password VARCHAR(45),
    d_numTelefono VARCHAR(45),

    -- Nueva fecha de nacimiento
    fec_nac DATE,

    -- Campos de dirección integrados
    dir_provin VARCHAR(45),
    dir_pobla VARCHAR(45),
    dir_infoExtra VARCHAR(255),

    -- Campos nuevos de intereses del usuario
    int_v1 VARCHAR(45),
    int_v2 VARCHAR(45),
    int_v3 VARCHAR(45),

    -- Campo para imagen (URL o ruta)
    imagen VARCHAR(255),

    -- Redes sociales
    url_twitter VARCHAR(255),
    url_facebook VARCHAR(255),
    url_linkedin VARCHAR(255),
    url_instagram VARCHAR(255),

    -- Otros campos
    id_interes INT,
    d_rol ENUM('GRATUITO','PREMIUM'),

    FOREIGN KEY (id_interes) REFERENCES Intereses(id_interes)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
