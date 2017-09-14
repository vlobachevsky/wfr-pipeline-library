#!/usr/bin/env groovy

/**
 * vars/sendNotifications.groovy
 *
 * This function uses emailtext to send an email.
 * @param emailRecipients	[null|string]												The email recipients
 * @param subject			["<JOB_NAME> - Build #<BUILD_NUMBER> - <result>!" | string] The email subject
 * @param attachLog			[null|true|false]											By default the buildlog will be included when the build fails
 */

def call(Map params = [:]) {
    def to = params.to ?: 'Vital.Lobachevskij@Kronos.com'
    def subject = params.subject ?: "${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}!"
    def body = params.body ?: "$env.DEFAULT_CONTENT"

    emailext (
        to: to,
        subject: subject,
        body: body,
        mimeType: 'text/html'
    )
}
