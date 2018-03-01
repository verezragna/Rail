  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (1, '1', '1', '1', '1', 'ACTIVE');
  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (2, '2', '2', '2', '2', 'ACTIVE');
  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (3, '3', '3', '3', '3', 'ACTIVE');
  INSERT INTO users (id, first_name, last_name, login, password, status) VALUES (4, '4', '4', '4', '4', 'ACTIVE');

  INSERT INTO roles (name) VALUES ('admin');
  INSERT INTO roles (name) VALUES ('user');

  INSERT INTO roles_link (user_id, role_id) VALUES (1, 1);
  INSERT INTO roles_link (user_id, role_id) VALUES (2, 2);
  INSERT INTO roles_link (user_id, role_id) VALUES (3, 1);
  INSERT INTO roles_link (user_id, role_id) VALUES (4, 2);