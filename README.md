# SegundUM: preview version

> A second-hand marketplace: browse products and publish and manage your own.

![Java](https://img.shields.io/badge/Java-11-orange?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3-red?style=flat&logo=apachemaven&logoColor=white)
![JSF](https://img.shields.io/badge/JSF-2.3-green?style=flat)
![JPA](https://img.shields.io/badge/JPA-EclipseLink-blue?style=flat)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=flat&logo=docker&logoColor=white)
![University of Murcia](https://img.shields.io/badge/University%20of%20Murcia-E03B23?style=flat&logo=graduation-cap&logoColor=white)

## Overview

SegundUM is a second-hand marketplace that lets you publish, search for and manage products, with advanced search by description, maximum price, condition and category. It also supports a pickup location with coordinates and a description. Each user has their own panel to create and edit their products.

## Project status

> [!NOTE]
> This is an initial version of SegundUM. The full version will be developed at [ibracb/segundum](https://github.com/ibracb/segundum).

## Demo

<p align="center">
  <video src="https://github.com/user-attachments/assets/a4630b4e-f2b3-46c9-87e3-33bd5e3a8d37" controls width="800"></video>
</p>

## Project structure

```
segundum-preview/
├── assets/                            # Screenshots for the user guide
├── docs/                              # Additional documentation
├── segundumOlmosMartinez/             # Maven project
│   ├── src/main/
│   │   ├── java/                      # Java source code
│   │   ├── resources/                 # Classpath resources
│   │   └── webapp/                    # Web resources
│   └── pom.xml                        # Maven configuration
├── .env.example                       # Example environment variables
├── .gitignore                         # Files ignored by Git
├── compose.yaml                       # Docker Compose file
└── README.md                          # Main documentation
```

## Requirements

- **Java 11 or higher:** check with `java --version`
- **Maven 3 or higher:** check with `mvn --version`
- **Docker** and **Docker Compose:** check with `docker --version` and `docker compose version`

## Installation

Clone the repository:
```bash
git clone https://github.com/ibracb/segundum-preview.git
cd segundum-preview
```

## Configuration

Copy the [example environment file](.env.example) and edit credentials as you want:
```bash
cp .env.example .env
```
Each variable in [`.env.example`](.env.example) is commented with its description and an example value.

Create the JPA configuration from the [persistence.xml.example](segundumOlmosMartinez/src/main/resources/META-INF/persistence.xml.example):
```bash
cp segundumOlmosMartinez/src/main/resources/META-INF/persistence.xml.example segundumOlmosMartinez/src/main/resources/META-INF/persistence.xml
```

> **Note:** The credentials in `persistence.xml` (user, password, database name and port) must match those set in `.env`.

**Exposed ports:**
- **MySQL**: `localhost:6033` → container `3306`
- **phpMyAdmin**: `localhost:8081`

The JPA configuration is in `segundumOlmosMartinez/src/main/resources/META-INF/persistence.xml`. Tables are created automatically (`eclipselink.ddl-generation = create-or-extend-tables`).

## Compilation and execution

Start the database (MySQL + phpMyAdmin):
```bash
docker compose up -d
```

Compile and start the application with Jetty:
```bash
cd segundumOlmosMartinez
mvn jetty:run
```

> **Note:** On first start-up an administrator account is created (email: `admin@segundum.es`, password: `admin`), and all categories are preloaded from XML files in [`categoriasXML/`](segundumOlmosMartinez/src/main/resources/categoriasXML/).

## Web access

- **Application**: http://localhost:8080/segundum/
- **phpMyAdmin**: http://localhost:8081

## Documentation

The full documentation is available in [`docs/`](docs/).

## Academic context

- **Subject:** Distributed Applications
- **Degree:** BSc in Computer Engineering
- **University:** University of Murcia
- **Year:** 2025-2026

## Authors

- **Lucía Olmos Martínez** - [luciaolmosmartinez](https://github.com/luciaolmosmartinez)
- **Ibrahim Cherif Barry** - [ibracb](https://github.com/ibracb)
