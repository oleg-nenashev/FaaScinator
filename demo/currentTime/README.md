# Demo: Using FaaScinator with a simple CLI app

1. Ensure that FaaScinator is packaged
2. Run `make build` to build the demo
3. Run `make run-local` to run the demo locally or `make run-docker` to run the demo in Docker container

The command will start the image and expose the API server on port `8080`.
Then you can:

1. Get current time by opening http://localhost:8080
2. Get current time in Zurich timezone by opening http://localhost:8080/?arg=Europe/Zurich
3. Get help by opening http://localhost:8080/help
