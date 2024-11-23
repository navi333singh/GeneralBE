CREATE TABLE IF NOT EXISTS one_password (
        Password_id int not null AUTO_INCREMENT PRIMARY KEY,
        WebSite varchar(255),
        Username varchar(255),
        Password varchar(255),
        Description varchar(255),
        Last_modified varchar(255)
);