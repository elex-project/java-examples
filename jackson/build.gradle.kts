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
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.4")

    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.4")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.12.4")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.4")
}
