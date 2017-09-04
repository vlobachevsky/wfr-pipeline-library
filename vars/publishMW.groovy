#!/usr/bin/env groovy

def call(String repo) {
    powershell '''
        .\\zip.cmd
        $mwSource = 'c:\\MW'
        $mwZipPath = $env:MiddlewarePath + 'MW.zip'
        $mwExePath = $env:MiddlewarePath + 'MW.ex_'

        Write-Host 'Path is: '$mwZipPath

        CScript .\\zip.vbs $mwSource 'c:\\MW.zip'

        Copy-Item c:\\MW.zip -Destination $mwZipPath -Force
        Copy-Item c:\\MW.zip -Destination $mwExePath -Force
    '''
}
