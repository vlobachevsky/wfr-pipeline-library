#!/usr/bin/env groovy

def call() {
    bat 'ant -file PunchMW/build.xml BuildEclipseCompiler SetProperties'
}
