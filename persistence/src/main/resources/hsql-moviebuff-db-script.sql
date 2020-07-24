
CREATE TABLE IF NOT EXISTS roles (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	role_name VARCHAR(25),

	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	username VARCHAR(255),
	password VARCHAR(255),
	email VARCHAR(255),

	PRIMARY KEY(id),
	fk_role BIGINT NOT NULL,
	FOREIGN KEY (fk_role) REFERENCES public.roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS movies (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	name VARCHAR(255),
	duration TIME,
	description VARCHAR(255),
	rating FLOAT,
	genre VARCHAR(255),
	release_date TIMESTAMP,

	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS theater_address (
    id BIGINT IDENTITY NOT NULL,
    external_id VARCHAR(36),
    city VARCHAR (32),
    pin_code VARCHAR (8),
    state VARCHAR (32),
    street_number INT,
    landmark VARCHAR (32),
    external_id VARCHAR (36),

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS theaters (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	theater_name VARCHAR(32),

	PRIMARY KEY(id),
	fk_theater_address BIGINT NOT NULL,
	FOREIGN KEY (fk_theater_address) REFERENCES public.theater_address(id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS seats (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	seat_number INT,

	PRIMARY KEY(id),

	fk_theater BIGINT NOT NULL,
	FOREIGN KEY (fk_theater) REFERENCES public.theaters(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS screenings (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	start_time TIMESTAMP,
	end_time TIMESTAMP,

	PRIMARY KEY(id),
	fk_movie BIGINT NOT NULL,
	FOREIGN KEY (fk_movie) REFERENCES public.movies(id) ON DELETE CASCADE,
	fk_theater BIGINT NOT NULL,
    FOREIGN KEY (fk_theater) REFERENCES public.theaters(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservations (
	id BIGINT IDENTITY NOT NULL,
	external_id VARCHAR(36),
	reservation_name VARCHAR(255),
	num_reserved_seats INT,

	PRIMARY KEY(id),

	fk_user BIGINT NOT NULL,
	FOREIGN KEY (fk_user) REFERENCES public.users(id) ON DELETE CASCADE,

	fk_screening BIGINT NOT NULL,
	FOREIGN KEY (fk_screening) REFERENCES public.screenings(id) ON DELETE CASCADE
);

