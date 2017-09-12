#!/usr/bin/env groovy

// TODO: Too many technologies used (cmd, vb, powershell). Needs to be refactored.

def call(Map params = [:]) {
    def repo = params.repo

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
        exit $LastExitCode
    """)
    echo "Status: ${status}"
    if (status != 0) {
        powershell 'exit $LastExitCode'
    }
}
