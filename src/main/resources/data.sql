use oauth_example;

/** Oauth - populate the oauth_client_details table */
INSERT INTO oauth_example.oauth_client_details (client_id, client_secret, scope, authorized_grant_types, access_token_validity, additional_information)
VALUES
('web', '$2a$04$v5mdnb3OdMm4xLd9ORzfoutlZHxmqxBsx727.pW87vo.Umazkhh/2', 'read,write', 'authorization_code,password,refresh_token,implicit', '900', '{}')
ON DUPLICATE key UPDATE
client_secret = VALUES(client_secret),
scope = VALUES(scope),
authorized_grant_types = VALUES(authorized_grant_types),
access_token_validity = VALUES(access_token_validity),
additional_information = VALUES(additional_information);

INSERT INTO oauth_example.users (user_id, username, password, enabled) VALUES
  ('1', 'peter@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
  ('2', 'john@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
  ('3', 'katie@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);


INSERT INTO oauth_example.role (role_id, role) VALUES (1,'ROLE_CUSTOMER');
INSERT INTO oauth_example.role (role_id, role) VALUES (1,'ROLE_ADMIN');
INSERT INTO oauth_example.user_role (user_id, role_id) VALUES (1,1);
INSERT INTO oauth_example.user_role (user_id, role_id) VALUES (2,1);
INSERT INTO oauth_example.user_role (user_id, role_id) VALUES (3,1);

commit;

