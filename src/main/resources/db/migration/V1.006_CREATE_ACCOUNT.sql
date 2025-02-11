CREATE TABLE account
(
    account_id VARCHAR(255) NOT NULL,
    user_id    VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    `desc`     VARCHAR(255) NULL,
    bank_name  VARCHAR(255) NULL,
    balance    FLOAT        NOT NULL,
    type       SMALLINT NULL,
    is_default BIT(1)       NOT NULL,
    image_url  VARCHAR(255) NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_account PRIMARY KEY (account_id)
);