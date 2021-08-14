/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

plugins {
    id("elex-java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.beryx.jlink") version "2.23.1"
}
application {
    mainClass.set("kr.pe.elex.examples.FxmlApplication")
    mainModule.set("kr.pe.elex.examples")
}
javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}
dependencies {
    //implementation("org.openjfx:javafx-controls:11.0.2")
    //implementation("org.openjfx:javafx-fxml:11.0.2")
}
