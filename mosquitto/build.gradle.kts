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
    //implementation("org.eclipse.paho:org.eclipse.paho.mqttv5.client:1.2.5")
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")

    // https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on
    implementation("org.bouncycastle:bcprov-jdk15on:1.69")
    // https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk15on
    implementation("org.bouncycastle:bcpkix-jdk15on:1.69")
}
