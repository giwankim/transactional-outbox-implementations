plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.ktlint)
}

group = "com.giwankim"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation(libs.springBootStarterDataJpa)
    implementation(libs.springBootStarterFlyway)
    implementation(libs.springBootStarterKafka)
    implementation(libs.springBootStarterWebmvc)
    implementation(libs.flyway.mysql)
    implementation(libs.kotlin.reflect)
    implementation(libs.jackson.kotlin)
    implementation(libs.oshai.kotlinLogging)
    implementation(platform(libs.namastackOutboxBom))
    implementation(libs.namastackOutboxStarterJdbc)
    developmentOnly(libs.springBootDockerCompose)
    runtimeOnly(libs.mysql.connectorJ)
    testImplementation(libs.springBootStarterDataJpaTest)
    testImplementation(libs.springBootStarterFlywayTest)
    testImplementation(libs.springBootStarterKafkaTest)
    testImplementation(libs.springBootStarterWebmvcTest)
    testImplementation(libs.springBootTestcontainers)
    testImplementation(libs.kotlin.testJunit5)
    testImplementation(libs.testcontainers.junitJupiter)
    testImplementation(libs.testcontainers.kafka)
    testImplementation(libs.testcontainers.mysql)
    testImplementation(libs.ninjaSquad.springmockk)
    testRuntimeOnly(libs.junit.platformLauncher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
