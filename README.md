#Spring Boot Java application to post Reviews to Slack

This is a Spring Boot Java application that runs a web server and listens for a JSON payload, it then posts the payload to the desired slack channel in the form of a slack message containing an attachment.

The context of the JSON payload is that is a client review of a support agent having completed a phone call with the agent.

The payload contains the following fields:

- userName
- callId
- rating
- comment