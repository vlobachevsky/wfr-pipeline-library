#!/usr/bin/env groovy

    def call(Map params = [:]) {
        assert params.repo

        bat "ant -f PunchMW/build.xml -DpackageTo='${repo}' PackageClientMW"
    }
