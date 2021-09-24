FROM maven:3.8.1-adoptopenjdk-8

RUN useradd -rm -u 666 gitlab-runner \
    && mkdir /app \
    && chown -R gitlab-runner /app

USER gitlab-runner
WORKDIR /app

COPY pom.xml /app/pom.xml
COPY /src /app/src

RUN mvn test -Dtest=DependencyTest

# remove dummy test so that there's no trace we run that
RUN rm /app/target/surefire-reports/TEST-DependencyTest.xml

ENTRYPOINT ["mvn", "test"]
