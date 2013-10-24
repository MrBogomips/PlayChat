package actors

/**
 * Chat supervisor.
 * 
 * It's responsible to configure the chat environment.
 * It can create robots on user requests: in this case it verifies the user has the credentials to perform the task
 */
class Chat extends ChatBaseActor {
	val chatRef = self
}