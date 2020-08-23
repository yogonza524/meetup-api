# Weather API Mock

Simple mock REST API Server 

## Stack
- Golang
- Make
- Gorilla (Mux)
- Docker & Docker Compose

## Context
```bash
http://localhost/8087
```

## Endpoints
```bash
GET /weather/{city}/{when}
```
- city [string]: place, address, country
- when [date]: simple date with format YYYY-MM-DD