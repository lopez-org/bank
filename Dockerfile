FROM maven:3.8.1-openjdk-17-slim

ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"

ENV MVN_RUN="compile exec:java"