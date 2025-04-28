# PDFExtractor 📄➡️📝

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-1.8-orange.svg)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com)

A web-based application built with **Spring Boot** and **Core Java** that extracts text from uploaded PDF files using **Apache PDFBox**, stores extraction history in **MySQL**, and delivers results via a clean **Thymeleaf** UI.

---

## Features ✨
- **PDF Upload & Text Extraction**: Upload PDFs and extract text content server-side.
- **Download Results**: Save extracted text as `.txt` files.
- **Extraction History**: Track uploaded PDFs, status, and generated files in MySQL.
- **User-Friendly UI**: Simple interface built with HTML5, CSS3, and Thymeleaf.

## Tech Stack 🛠️
- **Backend**: Java 1.8, Spring Boot, Apache PDFBox
- **Database**: MySQL
- **Frontend**: HTML5, CSS3, Thymeleaf
- **Build Tool**: Maven

---

## Installation & Setup 🚀

### Prerequisites
- Java 1.8
- MySQL 8.0+
- Maven

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/pdfextractor.git
   cd pdfextractor
