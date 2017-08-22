#!/usr/bin/env groovy

def call() {
    bat 'ant BuildEclipseCompiler BuildJS'
}
