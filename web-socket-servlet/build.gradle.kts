/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

plugins {
    id("elex-war")
}

dependencies {
    providedCompile ("javax.servlet:javax.servlet-api:4.0.1")
    implementation("javax.websocket:javax.websocket-api:1.1")
}
