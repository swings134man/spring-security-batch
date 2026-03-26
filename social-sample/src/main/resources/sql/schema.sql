-- 사용자 정보 테이블
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,                            -- 내부 사용자 고유 ID

                       email VARCHAR(255),                                  -- 이메일 (소셜에서 받을 수도 있고, 없을 수도 있음)
                       nickname VARCHAR(100) NOT NULL,                      -- 서비스 닉네임 (추가 입력)
                       name VARCHAR(100),                                   -- 실제 이름 (소셜에서 제공되거나 선택 입력)
                       phone_number VARCHAR(30),                            -- 전화번호 (추가 입력)

                       profile_image_url VARCHAR(500),                      -- 프로필 이미지 URL (소셜 또는 사용자 입력)

                       status VARCHAR(20) NOT NULL DEFAULT 'PENDING',       -- 가입 상태 (PENDING, ACTIVE, BLOCKED, WITHDRAWN)
                       last_login_at TIMESTAMP,                             -- 마지막 로그인 시간

                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 생성일
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP   -- 수정일
);

-- 인증 수단 테이블 (소셜 로그인과 일반 로그인 모두 관리)
CREATE TABLE auth_accounts (
                               id BIGSERIAL PRIMARY KEY,                            -- 인증 계정 ID

                               user_id BIGINT NOT NULL,                             -- users.id FK
                               auth_type VARCHAR(20) NOT NULL,                      -- 로그인 타입 (LOCAL, KAKAO, NAVER)

                               provider_user_id VARCHAR(255),                       -- 소셜 고유 ID (카카오/네이버에서 받은 값)
                               login_id VARCHAR(255),                               -- 일반 로그인 ID (이메일 or username)
                               password_hash VARCHAR(255),                          -- 비밀번호 해시 (LOCAL일 때만 사용)

                               is_primary BOOLEAN NOT NULL DEFAULT TRUE,            -- 대표 로그인 여부 (확장용)

                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 생성일
                               updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 수정일

                               CONSTRAINT fk_auth_user
                                   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

                               CONSTRAINT uk_provider_user
                                   UNIQUE (auth_type, provider_user_id),            -- 소셜 계정 중복 방지

                               CONSTRAINT uk_login_id
                                   UNIQUE (login_id)                               -- 일반 로그인 ID 중복 방지
);