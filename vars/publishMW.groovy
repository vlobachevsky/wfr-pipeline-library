#!/usr/bin/env groovy

def call(Map params = [:]) {
    def repo = params.repo

//    echo 'Debug...'
//    echo "$repo"
    powershell '''
        .\\zip.cmd
        $mwSource = 'c:\\MW'
        $mwZipPath = '\\\\epbyminw1044.minsk.epam.com\\wfr-artifactory\\' + 'MW.zip'
        $mwExePath = '\\\\epbyminw1044.minsk.epam.com\\wfr-artifactory\\' + 'MW.ex_'

        Write-Output 'Path is: '$mwZipPath

        CScript .\\zip.vbs $mwSource 'c:\\MW.zip'

        Copy-Item c:\\MW.zip -Destination $mwZipPath -Force
        Copy-Item c:\\MW.zip -Destination $mwExePath -Force
    '''
}
