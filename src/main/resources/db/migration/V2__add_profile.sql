create table profile
(
    id          bigint auto_increment primary key,
    first_name        VARCHAR(255)   NOT NULL,
    last_name        VARCHAR(255)   NOT NULL,
    company        VARCHAR(255)   NOT NULL,
    FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
);