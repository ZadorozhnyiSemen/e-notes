DROP TABLE IF EXISTS User;
CREATE TABLE User
(
  id        BIGINT NOT NULL,
  username  VARCHAR(16),
  email     VARCHAR(32),
  password  VARCHAR(32),
  is_active BOOLEAN,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Tags;
CREATE TABLE Tags
(
  id      BIGINT NOT NULL,
  name    VARCHAR(32),
  user_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES User (id)
);

DROP TABLE IF EXISTS Notebook;
CREATE TABLE Notebook
(
  id      BIGINT NOT NULL,
  name    VARCHAR(64),
  user_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES User (id)
)