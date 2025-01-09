CREATE TABLE users(
    user_id UUID PRIMARY KEY UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(16),
    password VARCHAR(50) NOT NULL
);

CREATE TABLE user_properties(
    user_properties_id UUID PRIMARY KEY UNIQUE NOT NULL,
    description TEXT,
    photo BYTEA,
    company varchar(50),
    user_id UUID,
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);