CREATE TABLE IF NOT EXISTS user
(
    user_id       VARCHAR(255) NOT NULL,
    user_name     VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    first_name    VARCHAR(255) NULL,
    last_name     VARCHAR(255) NULL,
    date_of_birth datetime     NULL,
    image_url     VARCHAR(255) NULL,
    created_at    datetime     NULL,
    updated_at    datetime     NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);