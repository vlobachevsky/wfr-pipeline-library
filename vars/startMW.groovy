#!/usr/bin/env groovy

def call() {
    echo 'startMW() is not implemented yet'

/*
    def status = powershell(returnStatus: true, script: """
        Write-Host "Starting PunchMW..."
        (Get-WmiObject -computerName localhost Win32_Service -Filter "Name='HandPunchMW'").StartService() | out-null
        Write-Host "Successfully started."
    """)

    if (status != 0) {
        error "Start MW step has been failed."
    }
*/
}