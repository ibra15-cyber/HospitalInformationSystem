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
