create table bomkey.client_info
(

    id            bigint auto_increment
        primary key,
    client_id     varchar(255) null,
    client_name   varchar(255) null,
    client_secret varchar(255) null,
    client_desc   varchar(255) null,
    client_url    varchar(255) null,
    redirect_url  varchar(255) null,
    created_date  datetime(6)  null,
    modified_date datetime(6)  null
);

create table bomkey.user
(

    id            bigint auto_increment
        primary key,
    user_email    varchar(255) null,
    password      varchar(255) null,
    user_name     varchar(255) null,
    created_date  datetime(6)  null,
    modified_date datetime(6)  null
);

