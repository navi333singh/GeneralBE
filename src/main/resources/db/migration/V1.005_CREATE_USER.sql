CREATE TABLE user
(
    user_id       VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    first_name    VARCHAR(255) NULL,
    last_name     VARCHAR(255) NULL,
    date_of_birth VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    `role`        VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);