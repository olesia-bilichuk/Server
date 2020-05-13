# RMI Server

## How to launch

1. Install [Maven](https://maven.apache.org/install.html)
2. Build JAR 
    ```shell script
    mvn package
    ```
3. Run the JAR. You can pass the server name and port as command line arguments or leave them default
    ```shell script
    java -cp target\Server-1.0-SNAPSHOT.jar ua.edu.lpnu.dsct.server.Main [<server_name> <port>]
    ```