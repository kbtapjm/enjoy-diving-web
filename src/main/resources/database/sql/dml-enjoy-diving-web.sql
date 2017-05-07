-- USER_BASIC
INSERT INTO USER_BASIC(id, name, nickname, sex, country, status, profile, introduce, login_date) VALUES (1, '박재명', '검은몽스', 'M', 'korea', 0, '/profile.jpg', 'hi', null);

-- USER
INSERT INTO USER(id, email, password, user_basic_id) VALUES (1, 'kbtapjm@gmail.com', '1234', 1);

-- ROLE
INSERT INTO ROLE(id, role, role_name) VALUES (1, 'ADMIN', '관리자');
INSERT INTO ROLE(id, role, role_name) VALUES (2, 'MANAGER', '강사');
INSERT INTO ROLE(id, role, role_name) VALUES (3, 'USER', '사용자');

-- USER_ROLE
INSERT INTO USER_ROLE(user_id, role_id) VALUES (1, 1);


