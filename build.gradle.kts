plugins {
    id("java")
    id("war") // Apply the WAR plugin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Use compileOnly instead of providedCompile
    compileOnly("javax.servlet:javax.servlet-api:4.0.1") // Servlet API (provided by the container)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// Correctly configure the WAR task in Gradle Kotlin DSL
tasks.named<War>("war") {
    archiveFileName.set("NewPRJ.war") // Specify the name of the WAR file
    destinationDirectory.set(file("./build/libs")) // Correctly set the output directory for the WAR
}
