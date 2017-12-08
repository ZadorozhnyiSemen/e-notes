CREATE TABLE User
(
  id       INTEGER NOT NULL,
  username VARCHAR(16),
  email VARCHAR(32),
  is_active BOOLEAN,
  PRIMARY KEY (id)
);

INSERT INTO User (username, email, is_active) VALUES ('Semen', 'seemz@mail.ru', TRUE);