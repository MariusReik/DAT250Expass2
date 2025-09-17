plugins {
	java
}

group = "no.hvl.dat250"
version = "0.0.1-SNAPSHOT"
description = "DAT250 Expass4 JPA Experiment"

java {
	toolchain { languageVersion = JavaLanguageVersion.of(21) }
}

repositories { mavenCentral() }

dependencies {
	implementation("org.hibernate.orm:hibernate-core:7.1.1.Final")
	implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
	implementation("com.h2database:h2:2.3.232")

	testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

