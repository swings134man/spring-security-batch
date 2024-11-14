-- user
create table bomkey.user
(
    id            bigint auto_increment
        primary key,
    password      varchar(255) null,
    role          varchar(255) null,
    user_email    varchar(255) null,
    user_name     varchar(255) null,
    created_date  datetime(6)  null,
    modified_date datetime(6)  null
);

-- oauth_client
create table bomkey.oauth_clients
(
    id                            bigint auto_increment
        primary key,
    client_id                     varchar(255) null,
    client_name                   varchar(255) null,
    client_secret                 varchar(255) null,
    post_logout_redirect_uri      varchar(255) null,
    redirect_uri                  varchar(255) null,
    require_authorization_consent bit          not null,
    created_date                  datetime(6)  null,
    modified_date                 datetime(6)  null
);

-- rsa_keys
create table bomkey.rsa_keys
(
    id          bigint auto_increment
        primary key,
    identifier  varchar(255) null,
    key_id      varchar(255) null,
    private_key tinytext     null,
    public_key  tinytext     null
);