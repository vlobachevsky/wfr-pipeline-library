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
        CD .\\PunchMW
        .\\zip.cmd
        \$mwSource = 'c:\\MW'
        \$mwZipPath = '${repo}' + 'MW.zip'
        \$mwExePath = '${repo}' + 'MW.ex_'

        Write-Output 'Path is: '\$mwZipPath
        
        CScript .\\zip.vbs \$mwSource 'c:\\MW.zip'

        if (Test-Path \$mwZipPath) {
            Copy-Item c:\\MW.zip -Destination \$mwZipPath -Force
        } else {
            Write-Error 'Can't find path \$mwZipPath'
        }
        if (Test-Path \$mwExePath) {
            Copy-Item c:\\MW.zip -Destination \$mwExePath -Force
        } else {
            Write-Error 'Can't find path \$mwExePath'
        }
        exit \$LastExitCode
    """)

    echo "Status: ${status}"
    if (status != 0) {
        error "Publish MW step failed."
    }
}
