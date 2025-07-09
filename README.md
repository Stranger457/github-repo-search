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
git clone https://github.com/your-username/github-repo-search.git
cd github-search-api
mvn clean install
```

### Run the App

```bash
mvn spring-boot:run
```

App runs at: `http://localhost:8080`

---

## API Specifications

### 1.Search GitHub Repositories

- **Endpoint**: `POST /api/github/search`
- **Description**: Fetch repositories from GitHub API and store in the database.

#### Request Body

```json
{
  "query": "spring boot",
  "language": "Java",
  "sort": "stars"
}
```

#### Successful Response

```json
{
  "message": "Repositories fetched and saved successfully",
  "repositories": [
    {
      "id": 123456,
      "name": "spring-boot-example",
      "description": "An example repository for Spring Boot",
      "owner": "user123"
    }
  ]
}
```

---

### 2. Retrieve Stored Results

- **Endpoint**: `GET /api/github/repositories`
- **Description**: Retrieve stored repositories with optional filters.

#### Request Parameters

- `language` (optional): Filter by programming language
- `minStars` (optional): Minimum number of stars
- `sort` (optional): Sort by `stars`, `forks`, or `updated` (default: stars)

#### Example Request

```
GET /api/github/repositories?language=Java&minStars=100&sort=stars
```

#### Example Response

```json
{
  "repositories": [
    {
      "id": 123456,
      "name": "spring-boot-example",
      "description": "An example repository for Spring Boot",
      "owner": "user123",
      "language": "Java",
      "stars": 450,
      "forks": 120,
      "lastUpdated": "2024-01-01T12:00:00Z"
    }
  ]
}
```

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
