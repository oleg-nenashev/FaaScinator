<p align="center"><img src="docs/images/repo-header.png" alt="FaaScinator"></p>

[![GitHub Release](https://img.shields.io/github/release/oleg-nenashev/faascinator.svg)](https://github.com/oleg-nenashev//faascinator/releases) 
[![Follow @oleg-nenashev](https://img.shields.io/twitter/follow/oleg_nenashev.svg?style=social)](https://twitter.com/intent/follow?screen_name=oleg_nenashev) 

# FaaScinator - Java CLI Apps as serverless functions (preview)

Converts Java CLI apps into serverless [OpenFaaS](https://www.openfaas.com/) functions, 
powered by [Quarkus](https://quarkus.io/), [picocli](https://picocli.info/), [Docker](https://www.docker.com/) and [Adoptium JDK](https://adoptium.net/).
This project is under active development (and may not work).
Feedback is welcome!

## Quickstart

It will be quicker soon,
just a developer mode for now.

1. Go to the [function](./function) directory.
2. Run `mvn clean package` to build the executables.
   JDK 11 and Maven 3.6.3+ are required.
   Once built, you can experiment with FaaScinator as a common Quarkus service and HTTP interface.
3. Run `make build-cache` to build the Docker image with Maven cache. It will take a while.
4. Run `make build` to build the OpenFaaS compatible FaaScinator image.
   Templates are yet to be supported.
5. Run `make run` to run the Dockerized demo.
   It will expose the reactive service on port `8080`.

## Usage

### Running in CLI

FaaScinator can be launched as a standard Quarkus service.
It allows passing external CLI JAR and settings via
[Quarkus configuration sources](https://quarkus.io/guides/config-reference#configuration_sources).

The following command will start the service on port 8080:

```bash
java \
     -Dfaascinator.descriptiom="Snows the current time" \
     -Dfaascinator.mainClass="io.faascinator.demo.currenttime.CurrentTime" \
     -Dfaascinator.cliJar="demo/currentTime/cli-app/target/demo-current-time.jar" \
     -jar function/2_service/target/quarkus-app/quarkus-run.jar
     Europe/Zurich
```

See the demo [here](./demo/currentTime).

### Running in Docker

Coming soon!

### Running in OpenFaaS

Coming soon!

## Planned features

* Deeper integration with [Quarkus](https://quarkus.io/) via extensions.
* Serverless template for [OpenFaaS](https://www.openfaas.com/).
* Support for setting system properties and environment variables via requests.
* Conversion of [picocli](https://picocli.info/) to JSON schema for the invocation interface.
* Support for Java 17 and, unlikely, for Java 1.8.
* Future: support for [kohsuke/args4j](https://github.com/kohsuke/args4j) as a CLI provider.
* Future: support for packaging as a native executable with GraalVM.

## Design

This is how the FaaScinator design looks like.
And yes, it was initially drawn on a napkin during the lunch break!
[Proof in Twitter](https://twitter.com/oleg_nenashev/status/1408776830363082758).

![FaaScinator design](/docs/images/faascinator-design.png)

## License

All code is licensed under the [Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
Graphics, presentation slides and other materials are licensed under
[Creative Commons Attribution-ShareAlike 3.0 Unported License](https://creativecommons.org/licenses/by-sa/3.0/).

## Contributing

Contributor guidelines are coming soon.
Just submit GitHub issues to share feedback.
Any pull requests are welcome!
