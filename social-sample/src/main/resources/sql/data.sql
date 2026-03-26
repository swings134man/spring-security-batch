-- 테스트 로컬 계정 1건
-- loginId(email): tester@sample.com
-- password(plain): 1234
INSERT INTO users (email, nickname, name, phone_number, profile_image_url, status, role, last_login_at)
VALUES (
    'tester@sample.com',
    'tester',
    'Sample Tester',
    '010-0000-0000',
    NULL,
    'ACTIVE',
    'USER',
    CURRENT_TIMESTAMP
);

INSERT INTO auth_accounts (user_id, auth_type, provider_user_id, login_id, password_hash, is_primary)
VALUES (
    (SELECT id FROM users WHERE email = 'tester@sample.com'),
    'LOCAL',
    NULL,
    'tester@sample.com',
    '$2a$10$oXVF33Taz9HEZPuuLcWfLOb1K45LZqKxHAHlmxhmtpqag/LsJRv7m',
    TRUE
);
-- '$2a$10$7EqJtq98hPqEX7fNZaFWoO.H8fM6x4Q8VE3Sk2rYZRk5v0zzakDxG', -- password