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
    def credentialsId = params.containsKey('credentialsId')
    def url = params.containsKey('url')
    def localDir = params.containsKey('localDir') ?: '.'
    def depthOption = params.containsKey('depthOption') ?: 'infinity'

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
