# GitHub Repository Search API

A Spring Boot application that provides an interface to search and filter GitHub repositories by topic, language, and star count using GitHub’s public REST API. It supports pagination, custom filters, and a well-structured, testable backend architecture.

---

## Features

-  **Search GitHub Repos** using topic, language, and keyword filters
-  **GitHub REST API v3** integration
-  **Pagination support** for large result sets
-  **Configurable filters** (stars, topics, etc.)
-  **Unit + Integration tested** (JUnit + Mockito)
-  **Retry logic** on GitHub API failure
-  **RESTful APIs** ready for Postman or frontend use

---

## Tech Stack

| Layer       | Tech              |
| ----------- | ----------------- |
| Language    | Java 17+          |
| Framework   | Spring Boot 3.5.x |
| Build Tool  | Maven             |
| HTTP Client | Spring WebClient  |
| Testing     | JUnit 5 + Mockito |
| API Tool    | Postman           |

---

## Project Structure

<img width="489" alt="image" src="https://github.com/user-attachments/assets/637c4b5a-418b-4044-8389-e2f393aab068" />


---

## Installation & Running Locally

### Prerequisites

- Java 17+
- Maven 3.8+

### Clone and Build

```bash
git clone https://github.com/your-username/github-search-api.git
cd github-search-api
mvn clean install
```

### Run the App

```bash
mvn spring-boot:run
```

App runs at: `http://localhost:8080`

---

## API Endpoints

### 1. Search Repositories

**GET** `/api/github/search`

**Query Params:**

| Param    | Required | Description                  |
| -------- | -------- | ---------------------------- |
| topic    | No       | Filter by topic              |
| language | No       | Filter by language           |
| keyword  | No       | General search keyword       |
| minStars | No       | Filter by minimum star count |
| page     | No       | Page number (default: 0)     |
| size     | No       | Page size (default: 10)      |

**Example:**

```http
GET /api/github/search?topic=spring&language=java&minStars=50&page=0&size=5
```

**Response:**

```json
{
  "repositories": [
    {
      "name": "spring-boot",
      "description": "Spring Boot makes it easy to create stand-alone...",
      "stars": 68000,
      "language": "Java",
      "url": "https://github.com/spring-projects/spring-boot"
    }
  ],
  "pagination": {
    "page": 0,
    "size": 5,
    "totalElements": 500,
    "totalPages": 100
  }
}

---

##  Testing

Run unit and integration tests using:

```bash
mvn test
```

 Covers:

- API layer
- Service logic
- Retry + Fallback
- Pagination & filtering

---

## Rate Limiting

The GitHub API has a [rate limit](https://docs.github.com/en/rest/overview/resources-in-the-rest-api#rate-limiting). Add a GitHub token to increase your quota.

---

## Contact

- **Author:** Vallipi Chowdappa
- **Email:** chowdappavallipi@gmail.com
- **GitHub:** [github.com/Stranger457](https://github.com/Stranger457)

---------
