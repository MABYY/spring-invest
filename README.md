# Spring Invest

#### Overview

This is a Spring Boot application for a stock trading platform. It provides user management (registration, authentication, and profile management), stores data in a MySQL database and fetches stock market data from the MarketStack API.

## Features
- User Management: Register, login, and manage user profiles with JWT-based authentication.
- Market Data Integration: Fetches real-time and historical stock data from MarketStack API.
- MySQL Database: Stores user information and stock data
- RESTful APIs: Exposes endpoints for user operations and stock data retrieval.

## Prerequisites
- Java: JDK 17 or later
- Maven: 3.6.0 or later
- MySQL: 8.0 or later
- MarketStack API Key: Sign up at marketstack.com to obtain an API key
- IDE: IntelliJ IDEA, Eclipse, or similar (optional but recommended)

## Setup Instructions
1. Clone the Repository
2. Set environment to development
3. Edit database name
4. Edit .env file using your MarketStack credentials.

The application will be available at http://localhost:8080

## Using Docker
Run the application using Docker
1. Build images
```
docker-compose build
```
2. Run containter
```
docker-compose up
```
   
