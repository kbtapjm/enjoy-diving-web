-- 롤
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (1, 'ADMIN', '관리자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (2, 'MANAGER', '강사', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (3, 'USER', '사용자', NOW());

-- 유저 기본
INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (1, '박재명', '검은몽스', 0, 'korea', 0, '/profile.jpg', 'hi', now());

-- 유저
INSERT INTO USER(id, email, password, reg_date, user_basic_id) VALUES (1, 'kbtapjm@gmail.com', '$2a$10$lp9vOarYGZtOug1YOu3FYuPYizGgUFuAODgTs10STF4kjXfd6eeSu', now(), 1);

-- 유저 롤
INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (1, 1, now());


