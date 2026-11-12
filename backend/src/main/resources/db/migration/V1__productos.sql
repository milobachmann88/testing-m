CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    descripcion VARCHAR(300) NOT NULL,
    imagen VARCHAR(255) NOT NULL,
    image_alt VARCHAR(150),
    categoria VARCHAR(100) NOT NULL,
    personalizado BOOLEAN,
    precio DECIMAL(10,2) NOT NULL,
    material VARCHAR(150),
    tamano VARCHAR(50),
    colores JSON,
    stock INT NOT NULL,
    activo BOOLEAN NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;