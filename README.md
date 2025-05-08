# Hospital Management System

A comprehensive JavaFX application for managing hospital patients, hospitalizations, and related medical data.

## Overview

This Hospital Management System provides healthcare facilities with an intuitive interface to manage patient information, track hospitalizations, and streamline administrative tasks. Built with JavaFX and following MVC architecture, the application offers a responsive and user-friendly experience.

## Features

- **Patient Management**
  - Add, edit, view, and delete patient records
  - Search patients by name
  - View complete patient history
  - Export patient data to CSV

- **Hospitalization Tracking**
  - Record patient hospitalizations with admission and discharge dates
  - Track diagnosis, treatments, and attending physicians
  - Document medical procedures and medications

- **User Interface**
  - Clean, intuitive JavaFX interface
  - Table-based views with sorting and filtering
  - Form validation to ensure data integrity
  - Status updates for all operations

- **Data Management**
  - Database-backed persistence
  - Search and filtering capabilities
  - Data export functionality

## System Requirements

- Java 11 or higher
- JavaFX 11 or higher
- MySQL 5.7+ or MariaDB 10.2+
- Minimum 4GB RAM
- 100MB disk space

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/username/hospital-management-system.git
   ```

2. Configure database connection:
   - Edit `src/main/resources/database.properties` with your database credentials

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   java -jar target/hospital-management-system.jar
   ```

## Project Structure

```
hospital-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── ibra/
│   │   │   │   │   ├── entity/              # Data models
│   │   │   │   │   ├── dao/                 # Data access objects
│   │   │   │   │   ├── service/             # Business logic
│   │   │   │   │   ├── frontend/            # UI components
│   │   │   │   │   │   ├── controller/      # JavaFX controllers
│   │   │   │   │   │   ├── view/            # FXML layout files
│   │   │   │   │   ├── util/                # Utility classes
│   │   ├── resources/
│   │   │   ├── css/                         # Stylesheets
│   │   │   ├── fxml/                        # UI layouts
│   │   │   ├── images/                      # Image resources
│   │   │   ├── database.properties          # DB configuration
│   ├── test/                                # Unit tests
├── pom.xml                                  # Maven configuration
├── README.md
```

## Usage Guide

### Patient Management

1. **View Patients**
   - All patients are displayed in the main table
   - Click on a patient to view their details in the form below

2. **Add New Patient**
   - Click "Add New" button
   - Fill in required fields (Last Name, First Name, Phone)
   - Click "Save" to create the record

3. **Edit Patient**
   - Select a patient from the table
   - Click "Edit" button
   - Modify fields as needed
   - Click "Save" to update the record

4. **Delete Patient**
   - Select a patient from the table
   - Click "Delete" button
   - Confirm the deletion in the dialog

5. **Search Patients**
   - Enter a name in the search field
   - Click "Search" to filter the patient list
   - Click "Clear" to reset the search

### Hospitalization Management

1. **View Hospitalizations**
   - Select a patient and click "View Hospitalizations"
   - All hospitalizations for the selected patient are displayed

2. **Add Hospitalization**
   - Navigate to the hospitalization view for a patient
   - Click "Add New" button
   - Enter admission details
   - Click "Save"

3. **Edit Hospitalization**
   - Select a hospitalization record
   - Click "Edit" button
   - Modify fields as needed
   - Click "Save"

## Database Schema

The application uses the following database schema:

### Patients Table
```sql
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    address TEXT,
    telephone VARCHAR(20) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Hospitalizations Table
```sql
CREATE TABLE hospitalizations (
    hospitalization_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    admission_date DATE NOT NULL,
    discharge_date DATE,
    diagnosis TEXT,
    treating_doctor VARCHAR(100),
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);
```

## Development

### Prerequisites

- JDK 11+
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or NetBeans)
- MySQL or MariaDB

### Setup Development Environment

1. Import the project into your IDE as a Maven project
2. Configure the database connection in `database.properties`
3. Run the `DatabaseSetup` class to initialize the database schema

### Building from Source

```bash
# Clone the repository
git clone https://github.com/username/hospital-management-system.git

# Navigate to the project directory
cd hospital-management-system

# Build the project
mvn clean install

# Run the application
java -jar target/hospital-management-system.jar
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- JavaFX community for excellent UI framework
- MySQL team for reliable database system
- All contributors who have helped improve this system
