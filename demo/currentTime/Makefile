.PHONY: build
build:
	cd cli-app && mvn clean package

.PHONY: run-local
run-local:
	java -jar ../../function/3_service/target/quarkus-app/quarkus-run.jar

.PHONY: run-docker
run-docker:
	docker run --rm -p 8080:8080 \
	   -v $(CURDIR)/cli-app/target/demo-current-time.jar:/app/payload.jar \
 	   -e QUARKUS_FAASCINATOR_DESCRIPTION="Shows the current time" \
	   -e QUARKUS_FAASCINATOR_CLIJAR=/app/payload.jar \
	   -e QUARKUS_FAASCINATOR_MAINCLASS="io.faascinator.demo.currenttime.CurrentTime" \
	   ghcr.io/oleg-nenashev/faascinator:main
