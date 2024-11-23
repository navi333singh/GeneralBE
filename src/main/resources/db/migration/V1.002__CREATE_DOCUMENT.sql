create table if not exists documents
(
    document_id   int auto_increment
        primary key,
    data          MEDIUMBLOB   null,
    last_modified varchar(255) null,
    description   varchar(255) null,
    title         varchar(255) null,
    type          varchar(255) null
);

