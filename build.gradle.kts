val springVersion = "3.4.2"

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.skillcode"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // SPRING
    implementation("org.springframework.boot:spring-boot-starter:${springVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    implementation("org.springframework.boot:spring-boot-starter-web:${springVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springVersion}")
    developmentOnly("org.springframework.boot:spring-boot-devtools:${springVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springVersion}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.1.0")
    runtimeOnly("com.h2database:h2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.4")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
