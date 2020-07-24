CREATE SCHEMA IF NOT EXISTS smt;

CREATE TABLE IF NOT EXISTS roles (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	role_name VARCHAR(25),
	
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	username VARCHAR(255),
	password VARCHAR(255),
	email VARCHAR(255),
	
	PRIMARY KEY(id),
	role_id BIGINT NOT NULL,
	FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS movies (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	name VARCHAR(255),
	duration TIME,
	description VARCHAR(255),
	rating FLOAT,
	genre VARCHAR(255),
    release_date TIMESTAMP,

	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS theater_address (
    id BIGINT NOT NULL AUTO_INCREMENT,
    external_id VARCHAR(36) NOT NULL,
    city VARCHAR (32),
    pin_code VARCHAR (8),
    state VARCHAR (32),
    street_number INT,
    landmark VARCHAR (32),

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS theaters (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	theater_name VARCHAR(255),

	PRIMARY KEY(id),
	theater_address_id BIGINT NOT NULL,
    FOREIGN KEY (theater_address_id) REFERENCES theater_address(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS seats (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	seat_number INT,

	PRIMARY KEY(id),

	theater_id BIGINT NOT NULL,
	FOREIGN KEY (theater_id) REFERENCES theaters(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS screenings (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	start_time TIMESTAMP,
	end_time TIMESTAMP,

	PRIMARY KEY(id),
	movie_id BIGINT NOT NULL,
	FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
	theater_id BIGINT NOT NULL,
	FOREIGN KEY (theater_id) REFERENCES theaters(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservations (
	id BIGINT NOT NULL AUTO_INCREMENT,
	external_id VARCHAR(36) NOT NULL,
	reservation_name VARCHAR(255),
	num_reserved_seats INT,

	PRIMARY KEY(id),

	user_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

	screening_id BIGINT NOT NULL,
	FOREIGN KEY (screening_id) REFERENCES screenings(id) ON DELETE CASCADE
);

