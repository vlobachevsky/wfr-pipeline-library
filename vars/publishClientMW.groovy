#!/usr/bin/env groovy

def call(Map params = [:]) {
    assert params.repo

    def path = params.repo.replace("\\", "\\\\")
    def propertyfile = 'build_client_mw.properties'
    writeFile file: "${propertyfile}", text: "client.release.dir=${path}"
//    bat "ant -propertyfile='${propertyfile}' PackageClientMW"
    bat "ant -propertyfile=build_client_mw.properties PackageClientMW"
}
