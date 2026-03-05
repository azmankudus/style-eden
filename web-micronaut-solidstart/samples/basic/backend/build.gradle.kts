plugins {
    alias(libs.plugins.micronaut.application)
    alias(libs.plugins.shadow)
    alias(libs.plugins.micronaut.aot)
}

version = "0.1"
group = "com.example.webapi"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor(libs.micronaut.openapi.processor)
    annotationProcessor(libs.micronaut.http.validation)
    annotationProcessor(libs.micronaut.serde.processor)
    annotationProcessor(libs.micronaut.validation.processor)
    implementation(libs.micronaut.serde.jackson)
    implementation(libs.micronaut.management)
    implementation(libs.micronaut.validation)
    implementation(libs.reactor.core)
    compileOnly(libs.micronaut.openapi.annotations)
    compileOnly(libs.micronaut.http.client)
    runtimeOnly(libs.logback.classic)
    testImplementation(libs.micronaut.http.client)
    testRuntimeOnly(libs.junit.platform.launcher)
}


application {
    mainClass = "com.example.webapi.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.webapi.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}

// Frontend Integration Tasks
val frontendDir = file("${projectDir}/../frontend")

val buildFrontend = tasks.register<Exec>("buildFrontend") {
    workingDir = frontendDir
    commandLine("bun", "run", "build")
    inputs.dir(frontendDir.resolve("src"))
    outputs.dir(frontendDir.resolve(".output/public"))
}

val copyFrontend = tasks.register<Sync>("copyFrontend") {
    dependsOn(buildFrontend)
    from(frontendDir.resolve(".output/public"))
    into(layout.projectDirectory.dir("src/main/resources/public"))
}

val buildWithFrontend = tasks.register("buildWithFrontend") {
    group = "build"
    description = "Builds both frontend and backend"
    dependsOn(copyFrontend)
    dependsOn("build")
}

val runWithFrontend = tasks.register("runWithFrontend") {
    group = "application"
    description = "Runs the application with a fresh frontend build"
    dependsOn(copyFrontend)
    mustRunAfter(copyFrontend)
    finalizedBy("run")
}


