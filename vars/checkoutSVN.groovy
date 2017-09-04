#!/usr/bin/env groovy

// vars/checkoutSVN.groovy

def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def credentialsId = config.credentialsId
    def url = config.url
    def localDir = config.localDir ?: '.'
    def depthOption = conif.depthOption ?: 'infinity'

    checkout([
      $class: 'SubversionSCM',
      locations: [[
        credentialsId: "${credentialsId}",
        depthOption: "${depthOption}",
        ignoreExternalsOption: true,
        local: "${localDir}",
        remote: "${url}"
      ]],
      workspaceUpdater: [$class: 'UpdateWithRevertUpdater']
    ])
}

/*
def call(String credentialsId, String url, String localDir = '.', String depthOption = 'infinity') {
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
*/
