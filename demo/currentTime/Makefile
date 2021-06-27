.PHONY: build
build:
	cd cli-app && mvn clean package

# FIXME: It does not work :(

.PHONY: run-local
run-local:
	java \
	  -Dfaascinator.mainClass="io.faascinator.demo.currenttime.CurrentTime" \
	  -Dfaascinator.cliJar="cli-app/target/demo-current-time.jar" \
	  -jar ../../function/3_service/target/quarkus-app/quarkus-run.jar

#TODO: Add Docker run
