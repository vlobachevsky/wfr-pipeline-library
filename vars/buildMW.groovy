#!/usr/bin/env groovy

def call() {
    bat 'ant BuildEclipseCompiler SetProperties'
    //powershell 'Write-Output "Hello, World!"'
    def stdout = powershell(returnStdout: true, script: '''
        Write-Output 'Hello, World!'
    ''')
    println stdout

    bat "\"${tool 'MSBuild-Default'}\" /p:Configuration=Release /p:Platform=\"Any CPU\" /t:Rebuild ${WORKSPACE}\\src\\AccessControl\\Build\\Build.xml"
}
