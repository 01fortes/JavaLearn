plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("com.jsamkt.learn.reactive.ReactiveProgrammingDemo")
}

dependencies {
    // RxJava - Reactive Extensions for the JVM
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    
    // Project Reactor - Reactive library for building non-blocking applications
    implementation("io.projectreactor:reactor-core:3.5.11")
    implementation("io.projectreactor:reactor-test:3.5.11")
    
    // Reactive Streams API - Specification for asynchronous stream processing
    implementation("org.reactivestreams:reactive-streams:1.0.4")
}