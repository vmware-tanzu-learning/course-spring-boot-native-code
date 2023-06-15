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

## Run the image you just built

Run the Docker container:
```
docker run -it -p 8080:8080 -v $(pwd):/mnt/workspace:delegated native-image
```
From within the container, check that the `native-image` application is installed and working:

```
native-image --version
```

Also, observe that the directory from your local machine is available within the image also:
```console
root@ad839a8cd71e:/mnt/workspace# ls
Dockerfile  LICENSE.txt  build         gradle   gradlew.bat  native-x86  settings.gradle
HELP.md     README.md    build.gradle  gradlew  native-arm   scripts     src
```

## Build the application:

Still from within the Docker container, do the fo0llowing:

Build the application as a regular JVM app:
(TODO Do I need to do this really?)
```
./gradlew build
```

Build the application as a native GraalVM app:
__Note__: This will take much longer than the non-native image. Count on 2+ minutes.
```
./gradlew nativeCompile
```

## Run the native app
```
build/native/nativeCompile/demo-web-native
```

## Test the app

__Note__ that port 8080 is exported from the Docker container to your local machine, so that you can contact the app that is running in the container from the machine from which you ran `docker run`.

-either-: From a browser on your local machine, navigate to [the test REST endpoint](http://localhost:8080/the-answer)

-or-: Use the command line, e.g. with the `curl` app:

```console
# curl http://localhost:8080/the-answer
```

You should receive the answer, which is 42.
