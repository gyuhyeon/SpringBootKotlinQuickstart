# Spring Boot Quickstart Project

The project is not yet complete, there will be a LOT of changes and force pushes for now.  
Instructions on setting up the project for yourself will be written when it's moderately complete.

## What is this?
This is a sample project made with IndustryStandards™ unironically.  
It has all the basic stuff you'll need for bootstrapping most projects you want to create, with minimum hassle.  
It's also great for school projects and such when you want to flex with proper project structure for no reason.  

## How to use
You'll need a server, a mysql database, and more depending on the modules you'd like to use.

### Local builds
- Open the project in IntelliJ.  
  - JDK 1.8.0_161 or higher is required. This project uses AES-256 encryption, which requires either jdk higher than 1.8.0_161 or separate JCE to be installed.  
- Database is required even when running locally.
  - `core/src/main/resources-local/properties/db.properties` should be set up in plaintext(don't commit the changes though!).
  - if you do not wish for a database for some reason, you'll have to delete `DataSourceConfig` along with all Beans that depend on any `@Repository` beans.
- Once that's set up, simply run `main()` in `FrontendApplication.kt`.

### Deployment
- Build an "uber" .jar file containing everything we need in a module
  - `mvn clean package -Preal -Dmaven.compiler.failOnError=false -Dmaven.test.skip=true -pl frontend -am`
    - `-P${phase}` sets the maven phase which decides the resource folder to use.
    - `-pl ${moduleToBuild}` decides which module you're building.
  - the jar file will be created inside the `target` folder of the project.
- Run it
  - run the jar file.
    ```shell script
    #!/bin/bash
    
    SERVER_PORT="8080"
    LOG_FOLDER="/home/ubuntu/logs/tomcat/frontend"
    JAVA_OPTS="-server -Xms256m -Xmx256m -XX:+UseG1GC -verbose:gc -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_FOLDER/heap-was1.hprof"
    EXEC_FILE="/home/ubuntu/deploy/frontend.jar"
    CONSOLE_LOG="$LOG_FOLDER/springboot_$(date +%Y%m%d).log"
    RUN_ARGS="--spring.profiles.active=real"
    
    nohup java $JAVA_OPTS -jar $EXEC_FILE --server.port=$SERVER_PORT $RUN_ARGS >> $CONSOLE_LOG &
    ```
  - the variables are self-explanatory. Change them appropriately.
  - `--spring.profiles.active=${phase}` is different from the maven build phase. This is the spring profile which affects `@Profile` and profile-specific stuff in `application.yaml`.
  
### Setting up an Nginx reverse proxy
- When exposing your web service to the public, Nginx reverse proxy is a good idea instead of using the Embedded Tomcat(WAS) of Spring Boot.