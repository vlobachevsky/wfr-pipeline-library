#!/usr/bin/env groovy

def call(Map params = [:]) {
    assert params.repo

    def propertyfile = 'build_client_mw.properties'
    writeFile file: "${propertyfile}", text: "client.release.dir=${params.repo}"
//    bat "ant -propertyfile='${propertyfile}' PackageClientMW"
    bat "ant -propertyfile='./${propertyfile}' Test"
}
