-- 어드민 등록
INSERT INTO member (email, username, password, role)
SELECT 'admin@dana.com', 'admin', '$2a$12$1cUD7jhX0JO16fm0UO6fHOniLK12Zil7ZjVsoZgBK0BJMjUPUrlPC', 'ADMIN'
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM member WHERE email = 'admin@dana.com');