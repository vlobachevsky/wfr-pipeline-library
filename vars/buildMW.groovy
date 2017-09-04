#!/usr/bin/env groovy


def call() {
    def msbuild = "${tool 'MSBuild-Default'}"

    compileMW()
    makeAssemblyInfo()
    compileAC()
}


private compileMW() {
    bat 'ant BuildEclipseCompiler SetProperties'
}

private makeAssemblyInfo() {
    powershell '''
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

        $text | Set-Content "$path\\src\\AccessControl\\Global\\GlobalAssemblyInfo.cs"
    '''
}

private compileAC() {
    // bat "\"${tool 'MSBuild-Default'}\" /p:Configuration=Release /p:Platform=\"Any CPU\" /t:Rebuild ${WORKSPACE}\\src\\AccessControl\\Build\\Build.xml"
    bat "${msbuild} /p:Configuration=Release /p:Platform=\"Any CPU\" /t:Rebuild ${WORKSPACE}\\src\\AccessControl\\Build\\Build.xml"
}
