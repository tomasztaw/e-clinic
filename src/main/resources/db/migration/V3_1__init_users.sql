INSERT INTO zajavka_user (user_id, user_name, email, password, name, active)
VALUES (1, 'admin', 'admin@zajavka.pl', '$2a$12$3QLk0PSaPwQLMpI4ixo//OTo2phgGwouQGGYJKGLp1TaAs51LGzni', 'Admin', true);
INSERT INTO zajavka_role (role_id, role)
VALUES (1, 'ADMIN'), (2, 'USER');
INSERT INTO zajavka_user_role (user_id, role_id)
VALUES (1, 1), (1, 2);

INSERT INTO zajavka_user (user_id, user_name, email, password, name, active)
VALUES (2, 'user1', 'user1@zajavka.pl', '$2a$12$3QLk0PSaPwQLMpI4ixo//OTo2phgGwouQGGYJKGLp1TaAs51LGzni', 'User1', true);
INSERT INTO zajavka_user_role (user_id, role_id)
VALUES (2, 2);
