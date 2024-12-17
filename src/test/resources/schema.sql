CREATE TABLE IF NOT EXISTS work_role (
    role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL,
    creation_date DATE NOT NULL,
    PRIMARY KEY (role_id) );

