DROP TABLE IF EXISTS "User";
CREATE TABLE "User"
(
  id        BIGINT NOT NULL,
  username VARCHAR(16),
  email VARCHAR(32),
  is_active BOOLEAN,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Tags;
CREATE TABLE Tags
(
  id      BIGINT NOT NULL,
  name    VARCHAR(32),
  user_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES "User"
);