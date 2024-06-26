CREATE TABLE customer(
	id INT PRIMARY KEY NOT NULL,
	cpf VARCHAR(14) UNIQUE NOT NULL,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(60) NOT NULL,
	sex VARCHAR(2) NOT NULL,
	birthdate DATE NOT NULL,
	email VARCHAR(40) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	active BOOLEAN NOT NULL
);

CREATE TABLE address(
	id INT PRIMARY KEY NOT NULL,
	state VARCHAR(30) NOT NULL,
	city VARCHAR(30) NOT NULL,
	street VARCHAR(30) NOT NULL,
	district VARCHAR(30) NOT NULL,
	number VARCHAR(4) NOT NULL,
	cep VARCHAR(9) NOT NULL,
	complement VARCHAR(30) NOT NULL,
	customer_id INT NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer(id)
);

