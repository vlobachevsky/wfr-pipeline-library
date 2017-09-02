#!/usr/bin/env groovy

def call() {
    bat 'ant BuildEclipseCompiler SetProperties'
    //powershell 'Write-Output "Hello, World!"'
    bat 'msbuild -Version'
}
