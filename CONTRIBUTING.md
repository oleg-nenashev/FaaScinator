# Contributing

## How to contribute?

This project does **NOT** require contributors to sign a contributor license agreement.
You can start contributing right away, no prerequisites!

* Want to submit a patch? See a typo in documentation? Just submit a pull request!
* Try out FaaScinator and submit your feedback.
  * Use [GitHub Issues](https://github.com/oleg-nenashev/FaaScinator/issues) to submit feature requests or bug reports.
  * Use [GitHub Discussions](https://github.com/oleg-nenashev/FaaScinator/discussions)
* Got something working? Share your success story in social media, use the `#FaaScinator` hashtag.

Any other types of contributions are welcome!

## Building and Testing

Prerequisites:

* JDK 11
* Maven 3.6.3+
* Docker

### Building FaaScinator

1. Go to the [function](./function) directory.
2. Run `mvn clean package` to build the executables.
   Once built, you can experiment with FaaScinator as a common Quarkus service and HTTP interface.
3. Run `make build` to build the OpenFaaS compatible Docker image.
   Templates are yet to be supported.
   
### Testing FaaScinator builds in Docker

1. Run `make run` to run the Dockerized demo.
   It will expose the reactive service on port `8080`.
2. Follow the Quick Start guidelines for testing,

### Building Maven cache for Docker images

The Docker build flow caches Maven artifacts to speed up the build.
If you work on massive updates, it might be reasonable to update cache to speedup builds.

1. Go to the [function](./function) directory.
2. Run `make build-cache` to build the Docker image with Maven cache. It will take a while.
3. Run builds with the new cache as documented above

## Releasing

The repository has continuous delivery enabled for the `main` branch.
All merged changes will be automatically released.
Docker images will have the `main` tag.

To release a version, you need write permissions to the repository:

1. Go to the release drafts, and select the current release draft.
2. Define the version for the new release.
   [Semantic Versioning 2.0.0](https://semver.org/) standard should be used.
3. Copy-edit the changelog
4. Publish the release

Once the GitHub Release is published,
a GitHub Action will execute the build, produce and deploy the artifacts and Docker images.
All these artifacts will be tagged with the required release version.
