// vars/sendMail.groovy
/**
 * sendMail.groovy
 *
 * This function will use emailext (https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin) to send an email
 * - An email will always be send to the requestor (the one who started the job)
 * - In case of a failure the email will be send to the curlprits
 *
 * @param emailRecipients	[null|string]												The email recipients
 * @param subject			["<JOB_NAME> - Build #<BUILD_NUMBER> - <result>!" | string] The email subject
 * @param attachLog			[null|true|false]											By default the buildlog will be included when the build fails
 *
 */

def call(Map params = [:]) {
    def to = params.to ?: '${env.PROJECT_RECIPIENT_LIST}'
    def subject = params.subject ?: '${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!'

//    def content = '${JELLY_SCRIPT,template="static-analysis"}'
//    def content = '<p>${DEFAULT_REPLYTO}</p><p>${JELLY_SCRIPT,template="html"}</p><p>${FAILED_TESTS}</p>'
    def body = params.body ?: '${DEFAULT_CONTENT}'
//    def attachLog = (params.attachLog != null) ? params.attachLog : (currentBuild.currentResult != "SUCCESS") // Attach buildlog when the build is not successfull
    def attachLog = params.attachLog ?: (currentBuild.currentResult != "SUCCESS") // Attach buildlog when the build is not successfull

    // Send email
    emailext(
        body: body,
        mimeType: 'text/html',
        replyTo: '${DEFAULT_REPLYTO}',
        subject: subject,
        to: to,
        attachLog: attachLog
    )
}
