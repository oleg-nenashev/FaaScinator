##
# Builder image
##
FROM maven/3.8.1-adoptopenjdk-11 as builder

RUN mkdir /app
WORKDIR /app
ADD * /app/
ADD .mvn /app/.mvn/
RUN mvn clean package -b

##
# use the openfaas image to get the watchdog
##
FROM openfaas/of-watchdog:0.7.6 as watchdog

##
# Create new image
##
FROM adoptopenjdk:11.0.8_10-jdk-hotspot

COPY --from=builder /app/function/target/*-runner /function
COPY --from=watchdog /fwatchdog /fwatchdog

ENV cgi_headers="true"
ENV fprocess="/function"
ENV mode="http"
ENV upstream_url="http://127.0.0.1:8000"

ENV exec_timeout="20s"
ENV write_timeout="25s"
ENV read_timeout="25s"

HEALTHCHECK --interval=2s CMD ["/fwatchdog", "healthcheck"]

CMD ["/fwatchdog"]
