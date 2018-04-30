create schema oauth_example collate default;


create table oauth_example.role
(
  role_id int          not null
    primary key,
  role    varchar(255) null
)
  engine = MyISAM;

create table oauth_example.user_role
(
  user_id bigint not null,
  role_id int    not null,
  primary key (user_id, role_id)
)
  engine = MyISAM;

create index FKa68196081fvovjhkek5m97n3y
  on oauth_example.user_role (role_id);

create table oauth_example.users
(
  user_id  bigint       not null
    primary key,
  enabled  bit          not null,
  password varchar(255) not null,
  username varchar(255) not null,
  constraint UK_r43af9ap4edm43mmtq01oddj6
  unique (username)
)
  engine = MyISAM;


create table if not exists oauth_example.oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);


create table if not exists oauth_example.oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_example.oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
