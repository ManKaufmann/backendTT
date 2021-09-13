CREATE TABLE user_data
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    username     VARCHAR(255) NULL,
    userpassword VARCHAR(255) NULL,
    CONSTRAINT pk_user_data PRIMARY KEY (id)
);