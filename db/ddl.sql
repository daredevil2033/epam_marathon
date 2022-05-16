DROP TABLE IF EXISTS Operators;
DROP TABLE IF EXISTS Requests;

CREATE TABLE Requests (
                request_id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
                phone NUMERIC(9) NOT NULL,
                name VARCHAR(50) NOT NULL,
                request VARCHAR(100),
                status VARCHAR(20) NOT NULL,
                date TIMESTAMP NOT NULL,
                CONSTRAINT requests_pk PRIMARY KEY (request_id)
);


CREATE TABLE Operators (
                operator_id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
                name VARCHAR(50) NOT NULL,
                request_id INTEGER,
                CONSTRAINT operators_pk PRIMARY KEY (operator_id)
);


ALTER TABLE Operators ADD CONSTRAINT Requests_Operators_fk
FOREIGN KEY (request_id)
REFERENCES Requests (request_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;