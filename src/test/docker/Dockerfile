FROM java:8

COPY gradlew build.gradle settings.gradle /project/
COPY gradle /project/gradle
COPY classes /project/build/classes
COPY resources /project/build/resources

WORKDIR /project

ENTRYPOINT ["./gradlew"]




