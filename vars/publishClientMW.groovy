#!/usr/bin/env groovy

def call(Map params = [:]) {
    assert params.repo
    bat "ant -Dclient.release.dir=${params.repo} PackageClientMW"
}
