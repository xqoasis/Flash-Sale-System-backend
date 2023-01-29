USE skillup;

CREATE TABLE promotion
(
    promotion_id                  VARCHAR(36)         NOT NULL,
    promotion_name                VARCHAR(128)         NOT NULL,
    commodity_id        VARCHAR(36)         NOT NULL,
    original_price      INTEGER             NOT NULL,
    promotion_price     INTEGER             NOT NULL,
    start_time          TIMESTAMP           NOT NULL,
    end_time            TIMESTAMP           NOT NULL,
    status              INTEGER,
    total_stock         BIGINT              NOT NULL,
    available_stock     BIGINT              NOT NULL,
    lock_stock          BIGINT              NOT NULL,
    image_url           VARCHAR(64)         NOT NULL,
    PRIMARY KEY(promotion_id)
)   ENGINE = InnoDB     CHARSET = UTF8mb4;

CREATE INDEX idx_promotion_commodity_id ON promotion(commodity_id);
CREATE INDEX idx_promotion_start_time ON promotion(start_time);
CREATE INDEX idx_promotion_end_time ON promotion(end_time);
CREATE INDEX idx_promotion_status ON promotion(status);

INSERT INTO promotion(promotion_id, promotion_name, commodity_id, original_price, promotion_price, start_time, end_time,
                      status, total_stock, available_stock, lock_stock, image_url)
VALUES  (UUID(), 'Iphone13 low price', 'b859ee94-20f1-11ed-861d-0242ac120002', 999, 899, CURRENT_TIMESTAMP(), '2023-12-26 00:00:00', 1, 500, 500, 0, 'Iphone13'),
        (UUID(), 'MacBook Pro Black Friday Special', 'b859ee94-20f1-11ed-861d-0242ac120003', 1699, 1499, CURRENT_TIMESTAMP(), '2023-12-26 00:00:00', 1, 5000, 5000, 0, 'MacBookPro');
