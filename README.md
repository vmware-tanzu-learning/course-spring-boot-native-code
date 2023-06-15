# Building and running a GraalVM native application

The codebase was prepared as follows
- Go to https://start.spring.io/#!dependencies=native
- Add the Spring Web dependency
- Generate and extract the project
- Create a basic REST endpoint (`SimpleEndpointController` class)

What **you** need to do is as follows:

## Build a Docker image containing GraalVM
Copy the Dockerfile from that `native-*` directory, according to your architecture
```
cp native-x86/Dockerfile .
```

Build your Docker image:
```
docker build -t native-image .
```

## Build and run the native image

Run the Docker container:
```
docker run -it -p 8080:8080 -v $(pwd):/mnt/workspace:delegated native-image
```
From within the container, check native-image is installed and working:

```
native-image --version
```

Also observe the directory from your local machine is available within the image also:
```console
root@ad839a8cd71e:/mnt/workspace# ls
Dockerfile  LICENSE.txt  build         gradle   gradlew.bat  native-x86  settings.gradle
HELP.md     README.md    build.gradle  gradlew  native-arm   scripts     src
```

## Build the application:

Still from within the Docker container.

Build ther application as a regular JVM app:
```
./gradlew build
```

Build the application as a native GraalVM app:
(this will take much longer than the non-native image. Count on 2+ minutes)
```
./gradlew nativeCompile
```

## Run the native app
```
build/native/nativeCompile/demo-web-native
```

## Test the app

__Note__ that port 8080 is exported from the Docker container to your local machine, so that you can contact the app that is running in the container from your local machine.

From a browser on your local machine, navigate to [the test REST endpoint](http://localhost:8080/theanswer)

