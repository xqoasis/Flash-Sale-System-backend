USE skillup;
CREATE TABLE orders(
                       order_number            BIGINT              NOT NULL,
                       order_status            INTEGER             NOT NULL,
                       promotion_id            VARCHAR(36)         NOT NULL,
                       promotion_name          VARCHAR(128)        NOT NULL,
                       user_id                 VARCHAR(36)         NOT NULL,
                       order_amount            INTEGER             NOT NULL,
                       create_time             TIMESTAMP           NOT NULL,
                       pay_time                TIMESTAMP,
                       PRIMARY KEY(order_number)
) ENGINE = InnoDB   CHARSET = utf8mb4;

CREATE INDEX idx_user_id ON orders(user_id);
