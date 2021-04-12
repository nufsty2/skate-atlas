CREATE TABLE IF NOT EXISTS skatepark (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  lights BOOLEAN,
  free BOOLEAN,
  inside BOOLEAN,
  surface VARCHAR(30),
  address VARCHAR(255),
  postalCode INT,
  city VARCHAR(80),
  country VARCHAR(20),
  state VARCHAR(20),
  website VARCHAR(255),
  INDEX(country),
  INDEX(state)
) engine=InnoDB;