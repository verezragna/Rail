CREATE TABLE users (
  id    INTEGER PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  login  VARCHAR(50) UNIQUE ,
  password VARCHAR(255),
  status VARCHAR(50));

  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (1, '1', '1', '1', '1', 'ACTIVE');
  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (2, '2', '2', '2', '2', 'ACTIVE');
  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (3, '3', '3', '3', '3', 'ACTIVE');
  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (4, '4', '4', '4', '4', 'ACTIVE');