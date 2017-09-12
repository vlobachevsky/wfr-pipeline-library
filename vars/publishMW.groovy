#!/usr/bin/env groovy

// TODO: Too many technologies used (cmd, vb, powershell). Needs to be refactored.

def call(Map params = [:]) {
    def repo = params.repo

/*
    def status = powershell(returnStatus: true, script: """
        CD .\\PunchMW
        .\\zip.cmd
        \$mwSource = 'c:\\MW'
        \$mwZipPath = '${repo}' + 'MW.zip'
        \$mwExePath = '${repo}' + 'MW.ex_'

        Write-Output 'Path is: '\$mwZipPath

        CScript .\\zip.vbs \$mwSource 'c:\\MW.zip'

        Copy-Item c:\\MW.zip -Destination \$mwZipPath -Force
        Copy-Item c:\\MW.zip -Destination \$mwExePath -Force
        exit 1
    """)
*/
    def status = powershell(returnStatus: true, script: """
        if (Test-Path '${repo}') {
            Write-Output 'Cannot find path: ${repo}'
            Exit 1
        }

        CD .\\PunchMW
        .\\zip.cmd
        \$mwSource = 'c:\\MW'
        \$mwZipPath = '${repo}' + 'MW.zip'
        \$mwExePath = '${repo}' + 'MW.ex_'

        Write-Output 'Path is: '\$mwZipPath
        
        CScript .\\zip.vbs \$mwSource 'c:\\MW.zip'

        Copy-Item c:\\MW.zip -Destination \$mwZipPath -Force
        Copy-Item c:\\MW.zip -Destination \$mwExePath -Force

        Exit \$LastExitCode
    """)

    if (status != 0) {
        error "Publish MW step failed."
    }
}
