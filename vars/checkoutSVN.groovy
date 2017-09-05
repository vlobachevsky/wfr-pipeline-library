#!/usr/bin/env groovy

// vars/checkoutSVN.groovy

def call(Map params = [:]) {
    def credentialsId = params.credentialsId
    def url = params.url
    def localDir = params.localDir ?: '.'
    def depthOption = params.depthOption ?: 'infinity'

    checkout([
      $class: 'SubversionSCM',
      locations: [[
        credentialsId: "$credentialsId",
        depthOption: "$depthOption",
        ignoreExternalsOption: true,
        local: "$localDir",
        remote: "$url"
      ]],
      workspaceUpdater: [$class: 'UpdateWithRevertUpdater']
    ])

}
