# Text wrap

## Text wrapper web application with a single wrapper endpoint

### Build

The project uses gradle build system which is automatically downloaded by build scripts.

Windows:
```shell
gradlew build 
```

Linux/Mac:
```shell
./gradlew build 
```

### Run locally

The following command will start the application in embedded Tomcat.

The app will listen on port `8080`.
```shell
gradlew bootRun
```

### Configuration

Configuration file for local run is located in path `config/application.yaml`.

See [Externalized Configuration chapter](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config) in Spring Boot documentation for more options of externalized configuration.

|Name|Example value|Description|
|----|-------------|-----------|
|textwrap.lineWidthInCharacters|120|Expected line width in characters after text wrapping.|

### Usage

The application publishes a two http endpoint with JSON formatted communication.

1. Submit line break task

* Path: `/api/lineBreak`
* Method: `POST`
* Content-Type: `application/json`
* Example request: `"Some input text to be wrapped"`
* Example response: `"961c635b-5104-4c1d-bb10-81d0f9834d0f"`

Complete example using cURL:
```shell
curl --location --request POST 'http://localhost:8080/api/lineBreak' \
--header 'Content-Type: application/json' \
--data-raw '"Some input text to be wrapped"'
```

Possible HTTP status codes:

* `200 (OK)`: Line break task scheduled with the returned ID of the response body.
* `400 (Bad request)`: The request is malformed.

---

2. Query status of line break task

* Path: `/api/lineBreak/{id}`
* Method: `GET`
* Content-Type: `application/json`
* Example path parameter: `"961c635b-5104-4c1d-bb10-81d0f9834d0f"`
* Example response: `["Some input", "text to be", "wrapped"]`

Possible HTTP status codes:

* `200 (OK)`: Task completed and body contains the wrapped lines.
* `202 (Accepted)`: Task is accepted and scheduled but not completed yet.
* `404 (Not found)`: There is no task with the given ID.
* `400 (Bad request)`: The request is malformed.
