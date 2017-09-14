// vars/sendMail.groovy
/**
 * sendMail.groovy
 *
 * This function will use emailext to send an email
 * - An email will always be send to the requestor (the one who started the job)
 * - In case of a failure the email will be send to the curlprits
 *
 * @param emailRecipients	[null|string]												The email recipients
 * @param subject			["<JOB_NAME> - Build #<BUILD_NUMBER> - <result>!" | string] The email subject
 * @param attachLog			[null|true|false]											By default the buildlog will be included when the build fails
 *
 */

def call(Map params = [:]) {
    def subject = params.subject ? params.subject : "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${currentBuild.result}!"
    def content = '${JELLY_SCRIPT,template="static-analysis"}'
    def attachLog = (params.attachLog != null) ? params.attachLog : (currentBuild.result != "SUCCESS") // Attach buildlog when the build is not successfull

    // Allways send a mail to the requestor (the one who started the job)
    def to = []
    to << emailextrecipients([[$class: 'RequesterRecipientProvider']])

    // Append email recipients given by user
    if (params.emailRecipients != null) {
        to << params.emailRecipients
    }

    // Append Culprits when the build is not successfull
    if (currentBuild.result != "SUCCESS") {
        to << emailextrecipients([[$class: 'CulpritsRecipientProvider']])
    }

    to = to.join(',')

    // Send email
    emailext(body: content, mimeType: 'text/html',
            replyTo: '$DEFAULT_REPLYTO', subject: subject,
            to: to, attachLog: attachLog )
}
