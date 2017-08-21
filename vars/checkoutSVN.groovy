#!/usr/bin/env groovy

// vars/checkoutSVN.groovy

def call(String credentialsId, String url, String depthOption = 'infinity') {
    checkout([
      $class: 'SubversionSCM',
      locations: [[
        credentialsId: "$credentialsId",
        depthOption: "$depthOption",
        ignoreExternalsOption: true,
        local: '.',
        remote: "$url"
      ]],
      workspaceUpdater: [$class: 'UpdateWithRevertUpdater']
    ])
}
