#!/usr/bin/env groovy

/**
 * vars/sendMail.groovy
 *
 * This function uses emailext step (https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin) to send an email
 * - Email is sent in html format
 *
 * @param to            [env.PROJECT_RECIPIENT_LIST | string]							        The email recipients
 * @param subject		["$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!" | string]     The email subject
 * @param body  		[$DEFAULT_CONTENT | string]         	  								The email body
 * @param attachLog		[true | false]  											            By default the buildlog will be included when the build fails
 */
def call(Map params = [:]) {
    def to = params.to ?: "${env.PROJECT_RECIPIENT_LIST}"
    def subject = params.subject ?: '${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!'
    def body = params.body ?: '${DEFAULT_CONTENT}'
    // Attach buildlog when the build is not successfull
    def attachLog = params.attachLog ?: (currentBuild.currentResult != "SUCCESS")

    // Send email
    emailext(
        to: to,
        subject: subject,
        body: body,
        attachLog: attachLog,
        mimeType: 'text/html',
        replyTo: '${DEFAULT_REPLYTO}',
    )
}
