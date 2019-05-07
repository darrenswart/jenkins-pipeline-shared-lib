def call(Map change) {
  println("Validating change tracking inputs...")
  problem = false
  if (!change.changeId) {
    // if changeId is null/empty generate a UUID
    change.changeId = UUID.randomUUID().toString()
  }
  if (!change.changeTargetType) {
    change.changeTargetType = "Application"
  }
  if (!change.targetCloud) {
    change.targetCloud = "OPCS"
  }
  if (!change.targetPlatform) {
    change.targetPlatform = "OpenShift"
  }
  if (!change.summary) {
    println "PROBLEM: Summary must be provided"
    problem = true
  }
  if (!change.status || !(change.status.toLowerCase() == "started" || change.status.toLowerCase() == "completed")) {
    println "PROBLEM: Unknown status"
    problem = true
  }
  if (!change.statusReason || !(change.statusReason.toLowerCase() == "success" || change.statusReason.toLowerCase() == "failure")) {
    println "PROBLEM: Unknown status reason"
    problem = true
  }
  if (!change.code) {
    println "PROBLEM: Authentication Key (code) must be provided"
    problem = true
  }
  if (problem) {
    currentBuild.result = 'ABORTED'
    error("Aborting unable to send change tracking notification.  See PROBLEMS listed in the log.")
  }
  println "Change tracking validation complete."
  println "change id  = " + change.changeId
  println "target cloud  = " + change.targetCloud
  println "status = " + change.status

  // httpRequest consoleLogResponseBody: true,
  // contentType: 'APPLICATION_JSON',
  // httpMode: 'POST',
  // ignoreSslErrors: true,
  // requestBody: """
  // """
  // responseHandle: 'NONE',
  // validResponseCodes: '200',
  // url: ''
}
