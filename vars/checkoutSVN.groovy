#!/usr/bin/env groovy

// vars/checkoutSVN.groovy

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

def call(Map params = [:]) {
    println params
    def credentialsId = params.'credentialsId'
    def url = params.'url'
    def localDir = params.'localDir' ?: '.'
    def depthOption = params.'depthOption' ?: 'infinity'
    echo "credentialsId: $credentialsId"
    echo "url: $url"
    echo "localDir: $localDir"
    echo "depthOption: $depthOption"

/*
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
*/

}
