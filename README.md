> **⚠️ Work in Progress**: This project is currently under active development. Some features may be incomplete or subject to change.

# TLOPO Loot Tracker

[![Java Version](https://img.shields.io/badge/Java-24%2B-brightgreen)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/License-BSD_3--Clause-yellow.svg)](LICENSE)
[![Maven](https://img.shields.io/badge/Maven-3.10.1-blue)](https://maven.apache.org/)
[![Platform](https://img.shields.io/badge/Platform-Cross--Platform-lightgrey)](https://github.com/TheBanditOfRed/Library-Manager/releases)
[![Status](https://img.shields.io/badge/Status-Incomplete-red)](#project-status)

![icon.png](src/main/resources/icon/icon.png)

## Overview
A comprensive loot tracker for The Legend of Pirates Online (TLOPO). Built for those who want to keep track of how many chests they have opened before getting a legendary item. But also for those who want to keep track of their loot in general.

## Planned Features

- **Loot Management**: Track and organize your collected items
- **User-Friendly GUI**: Intuitive interface built with Java Swing
- **Cross-Platform**: Runs on Windows, macOS, and Linux
- **Persistent Storage**: Save, load, and export your loot data
- **Multi-Language Support**: Localized interface support (only planning on release in English but backend is ready for localization)
- **Hotkeys**: Quick modify loot data with customizable hotkeys

## Requirements

- **Java 24 or higher**
- **Operating System**: Windows, macOS, or Linux

## Development Setup

### Prerequisites
- Java Development Kit (JDK) 24+
- Apache Maven 3.6+
- Git

### Getting Started
1. Clone the repository:
   ```bash
   git clone https://github.com/TheBanditOfRed/Loot-Tracker.git
   cd Loot-Tracker
   ```

2. Build the project:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="LootTracker"
   ```

### Building JAR
```bash
# Create executable JAR with dependencies
mvn clean compile assembly:single

# Run the built JAR
java -jar target/loot-tracker-0.1.0-jar-with-dependencies.jar
```


## Project Status
|        Component         |    Status    | Description                                                                                                                                         | Platform Support |
|:------------------------:|:------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------|:----------------:|
|   **Core Application**   | ❌ Incomplete | The main library management system with all features implemented including user management, book management, borrowing system, and fine calculation |  Cross-Platform  |
| **Localisation Backend** | ✅ Completed  | Multi-language support backend                                                                                                                      |  Cross-Platform  |
|   **English Language**   |    🔄 WIP    | English language support is being actively worked on as more code is added                                                                          |  Cross-Platform  |
|   **Data Management**    | ❌ Incomplete | SQL or CSV data storage (not decided yet)                                                                                                           |  Cross-Platform  |
|  **Windows Installer**   | ❌ Incomplete | MSI installer package with bundled JRE, Start Menu integration, and desktop shortcuts                                                               |     Windows      |
|   **macOS Installer**    | ❌ Incomplete | DMG installer package with macOS-specific icons and application bundle structure                                                                    |      macOS       |
|   **Linux Installer**    | ❌ Incomplete | DEB package with Linux desktop integration and application shortcuts                                                                                |      Linux       |


## License
This project is licensed under the BSD 3-Clause License - see the [LICENSE](LICENSE) file for details.
