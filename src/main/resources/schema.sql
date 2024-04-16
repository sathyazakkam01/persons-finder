CREATE TABLE IF NOT EXISTS person (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name varchar(255) NOT NULL
    );


 CREATE TABLE IF NOT EXISTS location (
     reference_id BIGINT PRIMARY KEY,
     latitude DOUBLE NOT NULL,
     longitude DOUBLE NOT NULL,
     FOREIGN KEY (reference_id) REFERENCES person(id)
 );
