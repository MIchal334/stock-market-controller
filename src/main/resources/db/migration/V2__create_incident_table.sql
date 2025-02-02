CREATE TABLE IF NOT EXISTS incident (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100),
    action_amount INT,
    price_threshold FLOAT,
    action_id BIGINT,
    customer_email VARCHAR(255),
    FOREIGN KEY (action_id) REFERENCES action_type(id) ON DELETE CASCADE
);
