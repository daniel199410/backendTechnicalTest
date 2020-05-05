CREATE TABLE IF NOT EXISTS backendtechnicaltest.PATIENT (
    PATIENT_ID INT NOT NULL AUTO_INCREMENT ,
    ID VARCHAR(20) NOT NULL COMMENT 'Identification document' ,
    FIRST_NAME VARCHAR(255) NOT NULL COMMENT 'Patient first name' ,
    LAST_NAME VARCHAR(255) NOT NULL COMMENT 'Patient last name' ,
    EMAIL VARCHAR(255) NOT NULL COMMENT 'Contact email' ,
    PHONE VARCHAR(20) NOT NULL COMMENT 'Contact phone' ,
    PRIMARY KEY (PATIENT_ID)
)
ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_spanish2_ci
COMMENT = 'Table for patients info storage';

ALTER TABLE `patient` ADD UNIQUE(`ID`);