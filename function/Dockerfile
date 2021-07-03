##
# Builder image with caching
##
FROM ghcr.io/oleg-nenashev/faascinator-buidcache:buildcache as builder

RUN mkdir /app
WORKDIR /app
ADD . /app/
RUN mvn clean package

##
# Use the openfaas image to get the watchdog - a reverse proxy with monitoring
# https://github.com/openfaas/of-watchdog
##
FROM openfaas/of-watchdog:0.7.6 as watchdog

##
# Create new image
##
FROM adoptopenjdk:11.0.11_9-jdk-hotspot

COPY --from=builder /app/3_lib/target/quarkus-app/ /function
ENV QUARKUS_HTTP_PORT=9090

COPY --from=watchdog /fwatchdog /fwatchdog
ENV cgi_headers="true"
ENV fprocess="java -jar /function/quarkus-run.jar"
ENV mode="http"
ENV upstream_url="http://127.0.0.1:9090"
ENV exec_timeout="20s"
ENV write_timeout="25s"
ENV read_timeout="25s"

HEALTHCHECK --interval=2s CMD ["/fwatchdog", "healthcheck"]

CMD ["/fwatchdog"]
