-- 롤
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (1, 'ADMIN', '슈퍼운영자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (2, 'MANAGER', '일반운영자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (3, 'USER', '사용자', NOW());

-- 유저 기본
INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (1, '슈퍼운영자', '검은몽스', 0, 'korea', 0, '/profile.jpg', 'hi', now());
INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (2, '일반운영자', '젠킨스', 0, 'korea', 0, '/profile.jpg', 'hi', now());
INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (3, '사용자', '코비', 0, 'korea', 0, '/profile.jpg', 'hi', now());

-- 유저
INSERT INTO USER(id, email, password, reg_date, user_basic_id) VALUES (1, 'kbtapjm@gmail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', now(), 1);
INSERT INTO USER(id, email, password, reg_date, user_basic_id) VALUES (2, 'manager@gmail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', now(), 1);
INSERT INTO USER(id, email, password, reg_date, user_basic_id) VALUES (3, 'user@gmail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', now(), 1);

-- 유저 롤
INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (1, 1, now());
INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (2, 2, now());
INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (3, 3, now());


