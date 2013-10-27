package playchat.protocols

import playchat.models._
import akka.actor.ActorRef

trait JoinChatProtocol
object JoinChatProcotol {
  case class Joined(user: User, ref: ActorRef) extends JoinChatProtocol
  case class Quit(user: User) extends JoinChatProtocol
}

