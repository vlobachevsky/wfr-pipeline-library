#!/usr/bin/env groovy

def call() {
    echo 'stopMW() is not implemented yet'

/*
    def status = powershell(returnStatus: true, script: """
        Write-Host " "
        \$PunchMWService = Get-Service HandPunchMW
        if (\$PunchMWService.Status -ne "Stopped") {
            (Get-WmiObject -computerName localhost  Win32_Service -Filter "Name='HandPunchMW'").StopService() | out-null
            Write-Host "PunchMW has been stopped."
        } else {
            Write-Host "PunchMW is not running. Nothing to stop."
        }
        Write-Host " "
    """)

    if (status != 0) {
        error "Stop MW step has been failed."
    }
*/

}
