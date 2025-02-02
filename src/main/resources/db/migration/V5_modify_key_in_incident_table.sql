ALERT TABLE incident DROP CONSTRAINT customer_id;
ALERT TABLE incident DROP COLUMN customer_id;
ALERT TABLE incident ADD COLUMN customer_email VARCHAR(255);