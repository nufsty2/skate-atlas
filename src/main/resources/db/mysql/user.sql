CREATE DATABASE IF NOT EXISTS skate_atlas;

ALTER DATABASE skate_atlas
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

CREATE USER IF NOT EXISTS 'springuser'@'%' IDENTIFIED BY 'springuser';
GRANT ALL PRIVILEGES ON skate_atlas.* TO 'springuser'@'%';
