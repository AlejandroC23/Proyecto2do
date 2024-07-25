CREATE TABLE Personas (
    per_id INT AUTO_INCREMENT PRIMARY KEY,
    per_nombre VARCHAR(100) NOT NULL,
    per_apellido VARCHAR(100) NOT NULL,
    per_cedula VARCHAR(24) NOT NULL UNIQUE,
    per_usuario VARCHAR(50) NOT NULL,
    per_clave VARCHAR(24) NOT NULL,
    per_telefono VARCHAR(15) NOT NULL,
    per_correoElectronico VARCHAR(100) NOT NULL,
    per_direccion VARCHAR(200) NOT NULL
);

CREATE TABLE Encargados (
    enc_id INT AUTO_INCREMENT PRIMARY KEY,
    enc_titulo VARCHAR(100) NOT NULL,
    enc_cargo VARCHAR(100) NOT NULL,
    per_id INT NOT NULL,
    FOREIGN KEY (per_id) REFERENCES Personas(per_id)
);

CREATE TABLE Laboratorios (
    lab_id INT AUTO_INCREMENT PRIMARY KEY,
    lab_nombre VARCHAR(50) NOT NULL UNIQUE,
    lab_edificio INT NOT NULL,
    enc_id INT NOT NULL,
    FOREIGN KEY (enc_id) REFERENCES Encargados(enc_id)
);

CREATE TABLE Estudiantes (
    est_id INT AUTO_INCREMENT PRIMARY KEY,
    est_matricula VARCHAR(100) NOT NULL UNIQUE,
    est_finalizoMantenimiento BOOLEAN NOT NULL,
    car_id INT NOT NULL,
    est_nivelCarrera INT NOT NULL,
    per_id INT NOT NULL,
    FOREIGN KEY (per_id) REFERENCES Personas(per_id)
);

CREATE TABLE Carreras (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    car_obj VARCHAR(100) NOT NULL
);

CREATE TABLE Mantenimientos (
    mant_id INT AUTO_INCREMENT PRIMARY KEY,
    mant_fechaini DATE NOT NULL,
    mant_fechafin DATE NOT NULL,
    lab_id INT NOT NULL,
    FOREIGN KEY (lab_id) REFERENCES Laboratorios(lab_id)
);

CREATE TABLE Estado (
    esta_id INT AUTO_INCREMENT PRIMARY KEY,
    esta_obj VARCHAR(100) NOT NULL
);

CREATE TABLE Computadoras (
    comp_id INT AUTO_INCREMENT PRIMARY KEY,
    comp_codigo VARCHAR(50) NOT NULL UNIQUE,
    comp_marca VARCHAR(100) NOT NULL,
    comp_mantRealizado BOOLEAN NOT NULL,
    esta_id INT NOT NULL,
    lab_id INT NOT NULL,
    FOREIGN KEY (esta_id) REFERENCES Estado(esta_id),
    FOREIGN KEY (lab_id) REFERENCES Laboratorios(lab_id)
);

CREATE TABLE Detalle_Mantenimiento (
    det_mant_id INT AUTO_INCREMENT PRIMARY KEY,
    det_mant_obj VARCHAR(250) NOT NULL,
    mant_id INT NOT NULL,
    comp_id INT NOT NULL,
    FOREIGN KEY (mant_id) REFERENCES Mantenimientos(mant_id),
    FOREIGN KEY (comp_id) REFERENCES Computadoras(comp_id)
);

CREATE TABLE Tipo_Hardware (
    tip_had_id INT AUTO_INCREMENT PRIMARY KEY,
    tip_had_obj VARCHAR(100) NOT NULL
);

CREATE TABLE Tipo_Software (
    tip_soft_id INT AUTO_INCREMENT PRIMARY KEY,
    tip_soft_obj VARCHAR(100) NOT NULL
);

CREATE TABLE Hardware (
    had_id INT AUTO_INCREMENT PRIMARY KEY,
    had_descripcion VARCHAR(200) NOT NULL,
    had_nroSerie VARCHAR(100) NOT NULL,
    tip_had_id INT NOT NULL,
    esta_id INT NOT NULL,
    FOREIGN KEY (tip_had_id) REFERENCES Tipo_Hardware(tip_had_id),
    FOREIGN KEY (esta_id) REFERENCES Estado(esta_id)
);

CREATE TABLE Software (
    soft_id INT AUTO_INCREMENT PRIMARY KEY,
    soft_nombre VARCHAR(100) NOT NULL,
    soft_descripcion VARCHAR(200) NOT NULL,
    soft_version VARCHAR(100) NOT NULL,
    esta_id INT NOT NULL,
    tip_soft_id INT NOT NULL,
    FOREIGN KEY (esta_id) REFERENCES Estado(esta_id),
    FOREIGN KEY (tip_soft_id) REFERENCES Tipo_Software(tip_soft_id)
);

CREATE TABLE Estudiantes_Mantenimientos (
    est_id INT NOT NULL,
    mant_id INT NOT NULL,
    PRIMARY KEY (est_id, mant_id),
    FOREIGN KEY (est_id) REFERENCES Estudiantes(est_id),
    FOREIGN KEY (mant_id) REFERENCES Mantenimientos(mant_id)
);

CREATE TABLE Computadoras_Hardware (
    comp_id INT NOT NULL,
    had_id INT NOT NULL,
    PRIMARY KEY (comp_id, had_id),
    FOREIGN KEY (comp_id) REFERENCES Computadoras(comp_id),
    FOREIGN KEY (had_id) REFERENCES Hardware(had_id)
);

CREATE TABLE Computadoras_Software (
    comp_id INT NOT NULL,
    soft_id INT NOT NULL,
    PRIMARY KEY (comp_id, soft_id),
    FOREIGN KEY (comp_id) REFERENCES Computadoras(comp_id),
    FOREIGN KEY (soft_id) REFERENCES Software(soft_id)
);