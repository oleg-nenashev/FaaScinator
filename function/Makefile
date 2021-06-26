##
# Helper file for building the Docker images
##

.PHONY: run
run:
	docker run -p 8080:8080 onenashev/faascinator

.PHONY: build
build:
	docker build -t onenashev/faascinator -f Dockerfile .

.PHONY: build-cache
build-cache:
	docker build -t onenashev/faascinator:buildcache -f Dockerfile-buildcache .
