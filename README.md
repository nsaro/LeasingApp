# Vehicle Leasing App #
        This application is used for administrating the leasing contracts
    
# Technology Stack #
        The following technologies should be used:
        • Java 11
        • Spring Boot >= 2.5
        • MySQL/MariaDB Database (locally dockerized)
        • Gradle
        • Flyway database migration to create the initial schema
        • OpenAPI specification to generate server/client.

# How to run Junit Test #

        In the root directory of the project run the below commands
        
        ./gradlew test

# How to run Leasing Application using Docker compose file #

        In the root directory of the project run the below commands
        
        gradle openApiGenerate
        gradle build
        docker compose up
        
        Note : Docker needs to be installed on local machine
