#!/usr/bin/env groovy

def call() {
    bat 'ant BuildEclipseCompiler SetProperties'
    //powershell 'Write-Output "Hello, World!"'
    def stdout = powershell(returnStdout: true, script: '''
        $path = $ENV:WORKSPACE
        $year = Get-Date -Format yyyy
        $month = Get-Date -Format MM

        $text = "using System;
        using System.Reflection;

        [assembly: AssemblyVersion(`"6.52.$year.$month`")]
        [assembly: AssemblyFileVersion(`"6.52.$year.$month`")]

        [assembly: AssemblyCompany(`"Kronos Inc`")]
        [assembly: AssemblyProduct(`"Kronos Workforce Ready`")]
        [assembly: AssemblyCopyright(`"Copyright (c) 1996-$year Kronos Inc`")]
        [assembly: CLSCompliant(true)]"

        Write-Output $text
        $text | Set-Content "$path\\src\\AccessControl\\Global\\GlobalAssemblyInfo.cs"
    ''')
    println stdout

    bat "\"${tool 'MSBuild-Default'}\" /p:Configuration=Release /p:Platform=\"Any CPU\" /t:Rebuild ${WORKSPACE}\\src\\AccessControl\\Build\\Build.xml"
}
