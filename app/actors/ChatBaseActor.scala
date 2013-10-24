package actors

import akka.actor.Actor
import akka.actor.ActorRef

/**
 * Base class for all the actors within the system
 */
abstract class ChatBaseActor extends Actor {
  protected val chatRef: ActorRef
  
  def receive = {
    case _ => {}
  }
}