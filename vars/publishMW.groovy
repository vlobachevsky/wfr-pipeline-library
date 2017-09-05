#!/usr/bin/env groovy

def call(Map params = [:]) {
    def repo = params.repo

    echo 'Debug...'
    echo "$repo"
/*
    powershell '''
        .\\zip.cmd
        $mwSource = 'c:\\MW'
        $mwZipPath = $env:MiddlewarePath + 'MW.zip'
        $mwExePath = $env:MiddlewarePath + 'MW.ex_'

        Write-Output 'Path is: '$mwZipPath

        CScript .\\zip.vbs $mwSource 'c:\\MW.zip'

        Copy-Item c:\\MW.zip -Destination $mwZipPath -Force
        Copy-Item c:\\MW.zip -Destination $mwExePath -Force
    '''
*/
}
