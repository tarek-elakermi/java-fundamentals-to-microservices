CREATE TABLE IF NOT EXISTS book_report (
    book_id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    borrow_count INT DEFAULT 0
)