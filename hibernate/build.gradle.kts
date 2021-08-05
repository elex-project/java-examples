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
    implementation ("org.hibernate:hibernate-core:5.4.27.Final")

    runtimeOnly ("com.h2database:h2:1.4.200")
    runtimeOnly ("org.apache.derby:derby:10.15.2.0")
    runtimeOnly ("org.xerial:sqlite-jdbc:3.36.0.1")
    //implementation("com.zsoltfabok:sqlite-dialect:1.0")
}
