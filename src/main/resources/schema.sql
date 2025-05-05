-- Create database
CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

-- Employee table (superclass)
CREATE TABLE Employee (
                          emp_id INT PRIMARY KEY AUTO_INCREMENT,
                          surname VARCHAR(50) NOT NULL,
                          first_name VARCHAR(50) NOT NULL,
                          address VARCHAR(150) NOT NULL,
                          telephone VARCHAR(20) NOT NULL
);

-- Doctor table (subclass of Employee)
CREATE TABLE Doctor (
                        emp_id INT PRIMARY KEY,
                        speciality VARCHAR(50) NOT NULL,
                        FOREIGN KEY (emp_id) REFERENCES Employee(emp_id) ON DELETE CASCADE
);

-- Department table
CREATE TABLE Department (
                            dept_code VARCHAR(10) PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            building VARCHAR(50) NOT NULL,
                            director_id INT,
                            FOREIGN KEY (director_id) REFERENCES Doctor(emp_id) ON UPDATE CASCADE
);

-- Nurse table (subclass of Employee)
CREATE TABLE Nurse (
                       emp_id INT PRIMARY KEY,
                       rotation VARCHAR(20) NOT NULL,
                       salary DECIMAL(10, 2) NOT NULL,
                       dept_code VARCHAR(10) NOT NULL,
                       FOREIGN KEY (emp_id) REFERENCES Employee(emp_id) ON DELETE CASCADE,
                       FOREIGN KEY (dept_code) REFERENCES Department(dept_code) ON UPDATE CASCADE
);

-- Ward table
CREATE TABLE Ward (
                      ward_id INT PRIMARY KEY AUTO_INCREMENT,
                      ward_number INT NOT NULL,
                      bed_count INT NOT NULL,
                      dept_code VARCHAR(10) NOT NULL,
                      supervisor_id INT,
                      FOREIGN KEY (dept_code) REFERENCES Department(dept_code) ON UPDATE CASCADE,
                      FOREIGN KEY (supervisor_id) REFERENCES Nurse(emp_id) ON UPDATE CASCADE,
                      UNIQUE (dept_code, ward_number) -- Ensure ward numbers are unique within a department
);

-- Patient table
CREATE TABLE Patient (
                         patient_id INT PRIMARY KEY AUTO_INCREMENT,
                         surname VARCHAR(50) NOT NULL,
                         first_name VARCHAR(50) NOT NULL,
                         address VARCHAR(150) NOT NULL,
                         telephone VARCHAR(20) NOT NULL
);

-- Hospitalization table
CREATE TABLE Hospitalization (
                                 hospitalization_id INT PRIMARY KEY AUTO_INCREMENT,
                                 patient_id INT NOT NULL,
                                 ward_id INT NOT NULL,
                                 bed_number INT NOT NULL,
                                 diagnosis VARCHAR(200) NOT NULL,
                                 doctor_id INT NOT NULL,
                                 admission_date DATE NOT NULL,
                                 discharge_date DATE,
                                 previous_hospitalization_id INT,
                                 FOREIGN KEY (patient_id) REFERENCES Patient(patient_id) ON UPDATE CASCADE,
                                 FOREIGN KEY (ward_id) REFERENCES Ward(ward_id) ON UPDATE CASCADE,
                                 FOREIGN KEY (doctor_id) REFERENCES Doctor(emp_id) ON UPDATE CASCADE,
                                 FOREIGN KEY (previous_hospitalization_id) REFERENCES Hospitalization(hospitalization_id),
                                 UNIQUE (ward_id, bed_number, admission_date) -- Ensure bed is not double-booked
);

-- Insert sample data for testing
-- Insert employees
INSERT INTO Employee (emp_id, surname, first_name, address, telephone) VALUES
                                                                           (1, 'Smith', 'John', '123 Medical Drive, City', '555-1234'),
                                                                           (2, 'Johnson', 'Sarah', '456 Health Lane, City', '555-2345'),
                                                                           (3, 'Williams', 'Robert', '789 Hospital Road, City', '555-3456'),
                                                                           (4, 'Brown', 'Maria', '101 Doctor Street, City', '555-4567'),
                                                                           (5, 'Jones', 'David', '202 Nurse Avenue, City', '555-5678'),
                                                                           (6, 'Miller', 'Linda', '303 Treatment Way, City', '555-6789');

-- Insert doctors
INSERT INTO Doctor (emp_id, speciality) VALUES
                                            (1, 'Cardiology'),
                                            (3, 'Neurology'),
                                            (5, 'Pediatrics');

-- Insert departments
INSERT INTO Department (dept_code, name, building, director_id) VALUES
                                                                    ('CARD', 'Cardiology', 'Building A', 1),
                                                                    ('NEURO', 'Neurology', 'Building B', 3),
                                                                    ('PED', 'Pediatrics', 'Building C', 5);

-- Insert nurses
INSERT INTO Nurse (emp_id, rotation, salary, dept_code) VALUES
                                                            (2, 'Morning', 65000.00, 'CARD'),
                                                            (4, 'Evening', 68000.00, 'NEURO'),
                                                            (6, 'Night', 70000.00, 'PED');

-- Insert wards
INSERT INTO Ward (ward_id, ward_number, bed_count, dept_code, supervisor_id) VALUES
                                                                                 (1, 1, 10, 'CARD', 2),
                                                                                 (2, 2, 8, 'CARD', 2),
                                                                                 (3, 1, 12, 'NEURO', 4),
                                                                                 (4, 1, 15, 'PED', 6);

-- Insert patients
INSERT INTO Patient (patient_id, surname, first_name, address, telephone) VALUES
                                                                              (1, 'Davis', 'Michael', '111 Patient Lane, City', '555-7890'),
                                                                              (2, 'Wilson', 'Jennifer', '222 Recovery Road, City', '555-8901'),
                                                                              (3, 'Taylor', 'James', '333 Health Street, City', '555-9012');

-- Insert hospitalizations
INSERT INTO Hospitalization (hospitalization_id, patient_id, ward_id, bed_number, diagnosis, doctor_id, admission_date, discharge_date, previous_hospitalization_id) VALUES
                                                                                                                                                                         (1, 1, 1, 3, 'Chest pain', 1, '2025-04-01', NULL, NULL),
                                                                                                                                                                         (2, 2, 3, 5, 'Migraine', 3, '2025-04-02', '2025-04-10', NULL),
                                                                                                                                                                         (3, 3, 4, 7, 'Fever', 5, '2025-04-03', NULL, NULL);