create table if not exists folder
(
    folder_id   INTEGER auto_increment
        primary key,
    name          varchar(255) null,
    type          varchar(255) null,
    parent_id     INTEGER DEFAULT 0
);

