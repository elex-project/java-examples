/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

plugins {
    id("elex-java")
}

dependencies {
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")

    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}
