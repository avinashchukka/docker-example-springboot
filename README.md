Spring Boot Example with Docker
==================================
This example creates a [`Docker`](https://www.docker.com/) image from a [`Spring Boot`](https://projects.spring.io/spring-boot/) 
application using [`Spotify's Docker Maven plugin`](https://github.com/spotify/docker-maven-plugin).

## Build and Run
### Maven Build
Make sure you have `Maven` installed. Execute the following maven command from the directory of the 
parent project:
```
mvn clean install
```
It will create the Spring Boot executable JAR (docker-example-service/target/),`docker-example-springboot-1.0.jar`. 

### Run
To run the newly created Spring Boot JAR from the terminal:
```
java -jar docker-example-springboot-1.0.jar
```
This should start up the example application at port `8080`. The application can be accessed at `http://localhost:8080`

### Docker Build
Before you build the Docker image, make sure Docker is available in your environment.
Execute the following maven command from the directory of the parent project:
```
mvn clean package docker:build
```
This should build a Docker image named `docker-example-springboot`.

### Docker Run
Run the newly created Docker image, `docker-example`, by executing the 
[`docker run`](https://docs.docker.com/engine/reference/run/) command from the terminal:
```
docker run --rm -p 8080:8080  --name=cheetos docker-example-springboot
```
##### Options
* `--rm` option automatically clean up the container and remove the file system when the container exit.
* `--name` option names the Docker container as `cheetos`. In absence of the `--name` option, the Docker generates a 
random name for your container.
* [`-p 8080:8080`](https://docs.docker.com/engine/reference/run/#expose-incoming-ports) option publishes all 
exposed ports to the host interfaces. In our example, it is port `8080` is both `hostPort` and `containerPort` 

This should start up the example application and it can be accessed at `http://localhost:8080`

## Docker Commands
### List Container
Run the [`docker ps`](https://docs.docker.com/v1.11/engine/reference/commandline/ps/) to list all the containers.
To see all running containers, execute the following command:
```
bash-3.2$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
d03854fb7779        docker-example      "java -Djava.security"   7 seconds ago       Up 6 seconds        0.0.0.0:8080->8080/tcp   cheetos
bash-3.2$ 
```
To see all running containers including the non-running ones, execute the following command:
```
bash-3.2$ docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS                         PORTS                    NAMES
d03854fb7779        docker-example      "java -Djava.security"   About a minute ago   Up About a minute              0.0.0.0:8080->8080/tcp   cheetos
28b2cff9e7e6        docker-example      "java -Djava.security"   About an hour ago    Exited (0) About an hour ago                            indra1
d2720676c932        nginx               "nginx -g 'daemon off"   4 months ago         Exited (0) 4 months ago                                 webserver
```

### Remove Container
To remove a Docker container, execute [`docker rm`](https://docs.docker.com/v1.11/engine/reference/commandline/rm/) 
command. This will remove a non-running container.
```
C02R87CQG8WP:springboot k24095$ docker rm springboot-example
springboot-example 
```
To forcefully remove a running container
```
C02R87CQG8WP:springboot k24095$ docker rm -f springboot-example
springboot-example
```

### Stop Container
To stop a container, execute [`docker stop`](https://docs.docker.com/v1.11/engine/reference/commandline/stop/)
```
command:
bash-3.2$ docker stop springboot-example 
springboot-example 
```
##### Configuration Tags
* `imageName` specifies the name of our example Docker image, e.g, `docker-example`
* `dockerDirectory` specifies the location of the `Dockerfile`. The contents of the dockerDirectory will be 
copied into `${project.build.directory}/docker`. A [`Dockerfile`](https://docs.docker.com/engine/reference/builder/)
specfies all the instructions to be read by Docker while building the image.
* `include` specfies the resources to be included, which ion our case is `docker-example-springboot-1.0.jar`

## DockerFile
Here is the content of the example `Dockerfile`.
```
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD docker-example-springboot-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dapp.port=${app.port}", "-jar","/app.jar"]
LABEL maintainer "Avinash "
```

#### Instruction
* `FROM` instruction sets the Base Image for subsequent instructions. FROM must be the first non-comment 
instruction in the Dockerfile.
* `VOLUME` instruction creates a mount point with the specified name.
* `ADD` instruction copies from `<src>` and adds them to the filesystem of the image at the path `<dest>`.
* `RUN` instruction executes the command on top of the current image.
* `EXPOSE` instruction informs Docker that the container listens on the specified network ports at runtime.
* `ENV` instruction sets the environment variable
* `ENTRYPOINT` allows you to configure a container that will run as an executable.
* `LABEL` instruction adds metadata to an image.
