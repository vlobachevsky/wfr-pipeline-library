#!/usr/bin/env groovy

def call(Map params = [:]) {
    def to = params.to ?: 'Vital.Lobachevskij@Kronos.com'
    def subject = params.subject ?: "${env.PROJECT_NAME} - Build # ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}!"
    def body = params.body ?: "$env.DEFAULT_CONTENT"

    emailext(
        to: to,
        subject: subject,
        body: body
    )
}
