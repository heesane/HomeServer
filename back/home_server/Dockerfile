# Java 런타임을 포함하는 베이스 이미지로 시작
FROM amazoncorretto:17 AS builder

# 유지보수자 정보 추가
LABEL maintainer="tees3359@gmail.com"

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# ./gradlew 파일 권한 변경
RUN chmod +x ./gradlew

# gradle 빌드
RUN ./gradlew bootJar
FROM amazoncorretto:17

COPY --from=builder build/libs/*.jar app.jar

ENTRYPOINT [ "java","-jar","/app.jar" ]
VOLUME /tmp