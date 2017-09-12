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

/*
    def status = powershell(returnStatus: true, script: """
        if (Test-Path '${repo}') {
            Write-Output 'Cannot find path: ${repo}'
            Exit
        }
        Write-Output 'Sanity Check'

        CD .\\PunchMW
        .\\zip.cmd
        \$mwSource = 'c:\\MW'
        \$mwZipPath = '${repo}' + 'MW.zip'
        \$mwExePath = '${repo}' + 'MW.ex_'

        Write-Output 'Path is: '\$mwZipPath

        CScript .\\zip.vbs \$mwSource 'c:\\MW.zip'

        Copy-Item c:\\MW.zip -Destination \$mwZipPath -Force
        Copy-Item c:\\MW.zip -Destination \$mwExePath -Force

        if (Test-Path \$mwZipPath) {
            Copy-Item c:\\MW.zip -Destination \$mwZipPath -Force
        } else {
            Write-Output 'Cannot find path: '\$mwZipPath
            exit 1
        }
        if (Test-Path \$mwExePath) {
            Copy-Item c:\\MW.zip -Destination \$mwExePath -Force
        } else {
            Write-Output 'Cannot find path: '\$mwExePath
            exit 1
        }
        exit \$LastExitCode
    """)
*/

    def status = powershell(returnStatus: true, script: """
        if ( !(Test-Path '${repo}') ) {
            Write-Output 'Cannot find path: ${repo}'
            Exit
        }
    """)

    echo "Status: ${status}"
    if (status != 0) {
        error "Publish MW step failed."
    }
}
