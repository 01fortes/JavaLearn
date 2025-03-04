plugins {
    id("java")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    
    group = "com.jsamkt.learn"
    version = "1.0-SNAPSHOT"
    
    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    
    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:jupiter-api")
        testImplementation("org.junit.jupiter:jupiter-params")
        testRuntimeOnly("org.junit.jupiter:jupiter-engine")
        
        // Common utilities
        implementation("org.apache.commons:commons-lang3:3.13.0")
    }
    
    tasks.test {
        useJUnitPlatform()
    }
}