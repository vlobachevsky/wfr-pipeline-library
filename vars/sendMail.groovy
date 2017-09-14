// vars/sendMail.groovy
/**
 * sendMail.groovy
 *
 * This function uses emailext (https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin) to send an email
 * - An email will always be send to the requestor (the one who started the job)
 * - In case of a failure the email will be send to the curlprits
 *
 * @param to            [null|string]												The email recipients
 * @param subject		["<JOB_NAME> - Build #<BUILD_NUMBER> - <result>!" | string] The email subject
 * @param body  		[null|true|false]											By default the buildlog will be included when the build fails
 * @param attachLog		[null|true|false]											By default the buildlog will be included when the build fails
 *
 */

def call(Map params = [:]) {
    def to = params.to ?: "${env.PROJECT_RECIPIENT_LIST}"
    def subject = params.subject ?: '${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!'
    def body = params.body ?: '${DEFAULT_CONTENT}'
    def attachLog = params.attachLog ?: (currentBuild.currentResult != "SUCCESS") // Attach buildlog when the build is not successfull

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
