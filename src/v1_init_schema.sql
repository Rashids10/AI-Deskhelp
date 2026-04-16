CREATE DATABASE IF NOT EXISTS ai_deskhelp;

USE ai_deskhelp;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tickets (
    ticket_id INT PRIMARY KEY AUTO_INCREMENT,
    created_by INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status ENUM('OPEN', 'CLOSED', 'IN_PROGRESS') NOT NULL DEFAULT 'OPEN',

    CONSTRAINT fk_created_by 
        FOREIGN KEY (created_by) REFERENCES users(user_id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ticket_comment (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT NOT NULL,
    user_id INT NOT NULL,
    comment TEXT NOT NULL,

    CONSTRAINT fk_ticket_comment 
        FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_user_comment 
        FOREIGN KEY (user_id) REFERENCES users(user_id) 
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ai_suggestion (
    suggestion_id INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT NOT NULL,
    suggestion TEXT NOT NULL,

    CONSTRAINT fk_ticket_suggestion 
        FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);