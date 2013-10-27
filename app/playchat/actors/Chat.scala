package playchat.actors

import akka.actor._

import playchat.api.BaseActor
import playchat.models.{ User, Room }
import playchat.protocols.JoinChatProcotol._

/**
  * Chat supervisor.
  *
  * It's responsible to configure the chat environment.
  * It can create robots on user requests: in this case it verifies the user is authorized to perform the task
  */
class Chat extends BaseActor {
  /**
    * All the rooms within the system
    */
  private[this] var rooms = Map.empty[Room, ActorRef]

  val chatRef = self

  def receive = {
    case Joined(user, ref) => {}
  }
}