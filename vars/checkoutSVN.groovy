#!/usr/bin/env groovy

/**
 * vars/checkoutSVN.groovy
 *
 * This function uses checkout step (https://wiki.jenkins.io/display/JENKINS/Subversion+Plugin) to check out a SVN repo.
  *
 * @param url           [string]                                The repository URL. Required.
 * @param credentialsId [infra.getSVNCredentialsId() | string]  A Jenkins Credential ID. By default as specified in infra.groovy.
 * @param localDir  	['.' | string]                          A local directory (relative to the workspace root) where the code is checked out.
 * @param depthOption	['infinity' | string]                   The repository depth. See http://svnbook.red-bean.com/en/1.7/svn.advanced.sparsedirs.html for details
 */
def call(Map params = [:]) {
    assert params.url

    def url = params.url
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
