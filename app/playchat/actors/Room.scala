package playchat.actors

import akka.actor.Actor
import akka.actor.ActorRef
import playchat.models._
import playchat.api.BaseActor

class RoomActor private[actors] (val room: Room, val chatRef: ActorRef) extends BaseActor {
  def receive = {
    case "dummy" => {}
  }
}
