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

DROP TABLE IF EXISTS Notebook;
CREATE TABLE Notebook
(
  id      BIGINT NOT NULL,
  name    VARCHAR(64),
  user_id BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Note;
CREATE TABLE Note
(
  id          BIGINT NOT NULL,
  title       VARCHAR(64),
  content     VARCHAR(512),
  notebook_id BIGINT,
  tag_id      BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Tags;
CREATE TABLE Tags
(
  id      BIGINT NOT NULL,
  name    VARCHAR(32),
  user_id BIGINT,
  note_id BIGINT,
  PRIMARY KEY (id)
);

ALTER TABLE Notebook
  ADD FOREIGN KEY (user_id) REFERENCES User (id);
ALTER TABLE Note
  ADD FOREIGN KEY (notebook_id) REFERENCES Notebook (id);
ALTER TABLE Note
  ADD FOREIGN KEY (tag_id) REFERENCES Tags (id);
ALTER TABLE Tags
  ADD FOREIGN KEY (user_id) REFERENCES User (id);
ALTER TABLE Tags
  ADD FOREIGN KEY (note_id) REFERENCES Note (id);
