##
# Builder image
##
FROM onenashev/faascinator:buildcache as builder

RUN mkdir /app
WORKDIR /app
ADD . /app/
RUN mvn clean package

##
# use the openfaas image to get the watchdog
##
FROM openfaas/of-watchdog:0.7.6 as watchdog

##
# Create new image
##
FROM adoptopenjdk:11.0.11_9-jdk-hotspot

COPY --from=builder /app/function/2_lib/target/quarkus-app/* /function
COPY --from=watchdog /fwatchdog /fwatchdog

ENV cgi_headers="true"
ENV fprocess="java -jar /function/quarkus-run.jar"
ENV mode="http"
ENV upstream_url="http://127.0.0.1:8000"

ENV exec_timeout="20s"
ENV write_timeout="25s"
ENV read_timeout="25s"

HEALTHCHECK --interval=2s CMD ["/fwatchdog", "healthcheck"]

CMD ["/fwatchdog"]
