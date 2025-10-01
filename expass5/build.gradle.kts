plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("redis.clients:jedis:6.2.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("no.hvl.dat250.expass5.RedisTest")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
}