plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("com.jsamkt.learn.advancedmath.AdvancedMathDemo")
}

dependencies {
    // Apache Commons Math - for various mathematical algorithms
    implementation("org.apache.commons:commons-math3:3.6.1")
    
    // La4j - Linear Algebra library for Java
    implementation("org.la4j:la4j:0.6.0")
    
    // Hipparchus - Mathematical library with advanced functions
    implementation("org.hipparchus:hipparchus-core:2.3")
    
    // JScience - Java library for scientific calculations
    implementation("org.jscience:jscience:4.3.1")
}