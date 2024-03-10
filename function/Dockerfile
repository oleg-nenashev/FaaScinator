##
# Builder image with caching
##
FROM ghcr.io/oleg-nenashev/faascinator-buidcache:main as builder

RUN mkdir /app
WORKDIR /app
ADD . /app/
RUN mvn clean package -B -ntp -e

##
# Use the openfaas image to get the watchdog - a reverse proxy with monitoring
# https://github.com/openfaas/of-watchdog
##
FROM ghcr.io/openfaas/of-watchdog:0.9.11 as watchdog

##
# Create new image
##
FROM eclipse-temurin:17.0.10_7-jdk

COPY --from=builder /app/3_service/target/quarkus-app/ /function
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
