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

The application publishes a single http endpoint with JSON formatted communication.

* Path: `textwrap/wrap`
* Method: `POST`
* Content-Type: `application/json`
* Example request: `{ "text": "Some input text to be wrapped" }`

Complete example using cURL:
```shell
curl --location --request POST 'http://localhost:8080/textwrap/wrap' \
--header 'Content-Type: application/json' \
--data-raw '{ "text": "Some input text to be wrapped" }'
```