#!/usr/bin/env groovy

// vars/checkoutSVN.groovy

def call(Map params = [:]) {
    assert params.url

    def url = infra.getSVNRootURL() + params.url
    def credentialsId = params.credentialsId ?: infra.getSVNCredentialsId()
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
